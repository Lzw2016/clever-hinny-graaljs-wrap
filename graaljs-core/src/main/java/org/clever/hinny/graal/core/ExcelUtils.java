package org.clever.hinny.graal.core;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.clever.common.utils.excel.ExcelDataReader;
import org.clever.common.utils.excel.ExcelDataWriter;
import org.clever.common.utils.excel.ExcelRowReader;
import org.clever.common.utils.excel.dto.ExcelData;
import org.clever.common.utils.excel.dto.ExcelRow;
import org.clever.common.utils.tuples.TupleTow;
import org.clever.hinny.graaljs.utils.InteropScriptToJavaUtils;
import org.graalvm.polyglot.Value;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 作者：lizw <br/>
 * 创建时间：2020/08/21 16:15 <br/>
 */
public class ExcelUtils {

    public static final ExcelUtils Instance = new ExcelUtils();

    private final org.clever.hinny.core.ExcelUtils delegate;

    public ExcelUtils() {
        delegate = org.clever.hinny.core.ExcelUtils.Instance;
    }

    @SuppressWarnings("rawtypes")
    public ExcelDataReader<Map> createReader(Map<String, Object> configMap) {
        org.clever.hinny.core.ExcelUtils.ExcelDataReaderConfig config = toExcelDataReaderConfig(configMap);
        return delegate.createReader(config);
    }

    public ExcelDataWriter createWriter(org.clever.hinny.core.ExcelUtils.ExcelDataWriterConfig config) {
        return null;
    }

    @SuppressWarnings({"rawtypes"})
    public ExcelDataMap read(Map<String, Object> configMap) {
        org.clever.hinny.core.ExcelUtils.ExcelDataReaderConfig config = toExcelDataReaderConfig(configMap);
        ExcelDataReader<Map> excelDataReader = delegate.createReader(config);
        if (config.getSheetNo() != null) {
            excelDataReader.read().sheet(config.getSheetNo()).doRead();
        } else if (config.getSheetName() != null) {
            excelDataReader.read().sheet(config.getSheetName()).doRead();
        } else {
            excelDataReader.read().doReadAll();
        }
        if (excelDataReader.isEnableExcelData()) {
            return new ExcelDataMap(excelDataReader);
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public void write(Map<String, Object> configMap, List<Map> listData) {
        org.clever.hinny.core.ExcelUtils.ExcelDataWriterConfig config = toExcelDataWriterConfig(configMap);
        ExcelDataWriter excelDataWriter = delegate.createWriter(config);
        ExcelWriterBuilder writerBuilder = excelDataWriter.write();
        ExcelWriterSheetBuilder sheetBuilder = null;
        if (config.getSheetNo() != null) {
            sheetBuilder = writerBuilder.sheet(config.getSheetNo());
        }
        if (StringUtils.isNotBlank(config.getSheetName())) {
            sheetBuilder = writerBuilder.sheet(config.getSheetName());
        }
        Assert.notNull(sheetBuilder, "参数sheetNo、sheetName不能都为空");
        // 处理List<Map> listData
        List<List<Object>> lists = getListData(listData, config);
        sheetBuilder.doWrite(lists);
    }


    @SuppressWarnings("rawtypes")
    public static class ExcelDataMap implements Serializable {
        private final ExcelDataReader<Map> excelDataReader;

        public ExcelDataMap(ExcelDataReader<Map> excelDataReader) {
            this.excelDataReader = excelDataReader;
        }

        /**
         * 返回第一个页签数据
         */
        public ExcelData<Map> getFirstExcelData() {
            return excelDataReader.getFirstExcelData();
        }

        /**
         * 根据页签编号返回页签数据
         */
        public ExcelData<Map> getExcelData(int sheetNo) {
            return excelDataReader.getExcelData(sheetNo);
        }

        /**
         * 根据页签名称返回页签数据
         */
        public ExcelData<Map> getExcelData(String sheetName) {
            return excelDataReader.getExcelData(sheetName);
        }

        /**
         * Excel读取结果
         */
        public Map<String, ExcelData<Map>> getExcelSheetMap() {
            return excelDataReader.getExcelSheetMap();
        }
    }

    @SuppressWarnings("rawtypes")
    public static List<List<Object>> getListData(List<Map> listData, org.clever.hinny.core.ExcelUtils.ExcelDataWriterConfig config) {
        List<TupleTow<String, org.clever.hinny.core.ExcelUtils.ExcelWriterHeadConfig>> list = config.getHeadConfigs();
        List<String> propertyNames = list.stream()
                .filter(tupleTow -> StringUtils.isNotBlank(tupleTow.getValue1()))
                .map(TupleTow::getValue1)
                .collect(Collectors.toList());
        Assert.notEmpty(propertyNames, "columns配置不能是空");
        List<List<Object>> lists = new ArrayList<>(listData.size());
        for (Map map : listData) {
            List<Object> dataRow = new ArrayList<>(propertyNames.size());
            for (String propertyName : propertyNames) {
                Object obj = InteropScriptToJavaUtils.Instance.toJavaObjectForBase(map.get(propertyName));
                dataRow.add(obj);
            }
            lists.add(dataRow);
        }
        return lists;
    }

    @SuppressWarnings({"unchecked", "rawtypes", "DuplicatedCode"})
    private static org.clever.hinny.core.ExcelUtils.ExcelDataReaderConfig toExcelDataReaderConfig(Map<String, Object> configMap) {
        org.clever.hinny.core.ExcelUtils.ExcelDataReaderConfig config = new org.clever.hinny.core.ExcelUtils.ExcelDataReaderConfig();
        Object filename = configMap.get("filename");
        if (filename instanceof String) {
            config.setFilename((String) filename);
        }
        Object inputStream = configMap.get("inputStream");
        if (inputStream instanceof InputStream) {
            config.setInputStream((InputStream) inputStream);
        }
        Object limitRows = configMap.get("limitRows");
        if (limitRows instanceof Number) {
            config.setLimitRows(((Number) limitRows).intValue());
        }
        Object enableExcelData = configMap.get("enableExcelData");
        if (enableExcelData instanceof Boolean) {
            config.setEnableExcelData((Boolean) enableExcelData);
        }
        Object enableValidation = configMap.get("enableValidation");
        if (enableValidation instanceof Boolean) {
            config.setEnableValidation((Boolean) enableValidation);
        }
        Object excelReaderExceptionHand = configMap.get("excelReaderExceptionHand");
        if (excelReaderExceptionHand instanceof Map) {
            Map<String, Object> callBack = (Map<String, Object>) excelReaderExceptionHand;
            Object exceptionHand = callBack.get("exceptionHand");
            if (exceptionHand instanceof Function) {
                config.setExcelReaderExceptionHand((throwable, context) -> ((Function) exceptionHand).apply(new Object[]{throwable, context}));
            }
        }
        Object excelRowReader = configMap.get("excelRowReader");
        if (excelRowReader instanceof Map) {
            Map<String, Object> callBack = (Map<String, Object>) excelRowReader;
            Object readRow = callBack.get("readRow");
            Object readEnd = callBack.get("readEnd");
            boolean readRowExe = readRow instanceof Function;
            boolean readEndExe = readEnd instanceof Function;
            if (readRowExe || readEndExe) {
                config.setExcelRowReader(new ExcelRowReader<>() {
                    @SuppressWarnings("rawtypes")
                    @Override
                    public void readRow(Map data, ExcelRow<Map> excelRow, AnalysisContext context) {
                        if (readRowExe) {
                            ((Function) readRow).apply(new Object[]{data, excelRow, context});
                        }
                    }

                    @SuppressWarnings("rawtypes")
                    @Override
                    public void readEnd(AnalysisContext context) {
                        if (readEndExe) {
                            ((Function) readEnd).apply(new Object[]{context});
                        }
                    }
                });
            }
        }
        Object autoCloseStream = configMap.get("autoCloseStream");
        if (autoCloseStream instanceof Boolean) {
            config.setAutoCloseStream((Boolean) autoCloseStream);
        }
        Object extraRead = configMap.get("extraRead");
        if (extraRead instanceof Map) {
            Value arrayTmp = Value.asValue(extraRead);
            if (arrayTmp.hasArrayElements()) {
                List<CellExtraTypeEnum> extraReadList = new ArrayList<>();
                for (int i = 0; i < arrayTmp.getArraySize(); i++) {
                    Value item = arrayTmp.getArrayElement(i);
                    if (item.isString()) {
                        extraReadList.add(toCellExtraTypeEnum(item.asString()));
                    }
                }
                extraReadList = extraReadList.stream().filter(Objects::nonNull).collect(Collectors.toList());
                config.setExtraRead(extraReadList.toArray(new CellExtraTypeEnum[0]));
            }
        }
        Object ignoreEmptyRow = configMap.get("ignoreEmptyRow");
        if (ignoreEmptyRow instanceof Boolean) {
            config.setIgnoreEmptyRow((Boolean) ignoreEmptyRow);
        }
        Object mandatoryUseInputStream = configMap.get("mandatoryUseInputStream");
        if (mandatoryUseInputStream instanceof Boolean) {
            config.setMandatoryUseInputStream((Boolean) mandatoryUseInputStream);
        }
        Object password = configMap.get("password");
        if (password instanceof String) {
            config.setPassword((String) password);
        }
        Object sheetNo = configMap.get("sheetNo");
        if (sheetNo instanceof Number) {
            config.setSheetNo(((Number) sheetNo).intValue());
        }
        Object sheetName = configMap.get("sheetName");
        if (sheetName instanceof String) {
            config.setSheetName((String) sheetName);
        }
        Object headRowNumber = configMap.get("headRowNumber");
        if (headRowNumber instanceof Number) {
            config.setHeadRowNumber(((Number) headRowNumber).intValue());
        }
        Object useScientificFormat = configMap.get("useScientificFormat");
        if (useScientificFormat instanceof Boolean) {
            config.setUseScientificFormat((Boolean) useScientificFormat);
        }
        Object use1904windowing = configMap.get("use1904windowing");
        if (use1904windowing instanceof Boolean) {
            config.setUse1904windowing((Boolean) use1904windowing);
        }
        Object locale = configMap.get("locale");
        if (locale instanceof String) {
            config.setLocale(toLocale((String) locale));
        }
        Object autoTrim = configMap.get("autoTrim");
        if (autoTrim instanceof Boolean) {
            config.setAutoTrim((Boolean) autoTrim);
        }
        Object customObject = configMap.get("customObject");
        if (customObject != null) {
            config.setCustomObject(customObject);
        }
        Object columns = configMap.get("columns");
        if (columns instanceof Map) {
            Map<String, Object> columnsMap = (Map<String, Object>) columns;
            for (Map.Entry<String, Object> entry : columnsMap.entrySet()) {
                String columnName = entry.getKey();
                Object columnConfig = entry.getValue();
                if (StringUtils.isBlank(columnName) || !(columnConfig instanceof Map)) {
                    continue;
                }
                config.getColumns().put(columnName, toExcelReaderHeadConfig((Map<String, Object>) columnConfig));
            }
        }
        return config;
    }

    @SuppressWarnings({"unchecked", "DuplicatedCode"})
    private static org.clever.hinny.core.ExcelUtils.ExcelDataWriterConfig toExcelDataWriterConfig(Map<String, Object> configMap) {
        org.clever.hinny.core.ExcelUtils.ExcelDataWriterConfig config = new org.clever.hinny.core.ExcelUtils.ExcelDataWriterConfig();
        Object request = configMap.get("request");
        if (request instanceof HttpServletRequest) {
            config.setRequest((HttpServletRequest) request);
        }
        Object response = configMap.get("response");
        if (response instanceof HttpServletResponse) {
            config.setResponse((HttpServletResponse) response);
        }
        Object fileName = configMap.get("fileName");
        if (fileName instanceof String) {
            config.setFileName((String) fileName);
        }
        Object outputStream = configMap.get("outputStream");
        if (outputStream instanceof OutputStream) {
            config.setOutputStream((OutputStream) outputStream);
        }
        Object autoCloseStream = configMap.get("autoCloseStream");
        if (autoCloseStream instanceof Boolean) {
            config.setAutoCloseStream((Boolean) autoCloseStream);
        }
        Object inMemory = configMap.get("inMemory");
        if (inMemory instanceof Boolean) {
            config.setInMemory((Boolean) inMemory);
        }
        Object template = configMap.get("template");
        if (template instanceof String) {
            config.setTemplate((String) template);
        }
        Object templateInputStream = configMap.get("templateInputStream");
        if (templateInputStream instanceof InputStream) {
            config.setTemplateInputStream((InputStream) templateInputStream);
        }
        Object writeExcelOnException = configMap.get("writeExcelOnException");
        if (writeExcelOnException instanceof Boolean) {
            config.setWriteExcelOnException((Boolean) writeExcelOnException);
        }
        Object automaticMergeHead = configMap.get("automaticMergeHead");
        if (automaticMergeHead instanceof Boolean) {
            config.setAutomaticMergeHead((Boolean) automaticMergeHead);
        }
        Object excludeColumnFiledNames = configMap.get("excludeColumnFiledNames");
        if (excludeColumnFiledNames instanceof Map) {
            Value arrayTmp = Value.asValue(excludeColumnFiledNames);
            if (arrayTmp.hasArrayElements()) {
                for (int i = 0; i < arrayTmp.getArraySize(); i++) {
                    Value item = arrayTmp.getArrayElement(i);
                    if (item.isString()) {
                        config.getExcludeColumnFiledNames().add(item.asString());
                    }
                }
            }
        }
        Object excludeColumnIndexes = configMap.get("excludeColumnIndexes");
        if (excludeColumnIndexes instanceof Map) {
            Value arrayTmp = Value.asValue(excludeColumnIndexes);
            if (arrayTmp.hasArrayElements()) {
                for (int i = 0; i < arrayTmp.getArraySize(); i++) {
                    Value item = arrayTmp.getArrayElement(i);
                    if (item.isNumber()) {
                        config.getExcludeColumnIndexes().add(item.asInt());
                    }
                }
            }
        }
        Object includeColumnFiledNames = configMap.get("includeColumnFiledNames");
        if (includeColumnFiledNames instanceof Map) {
            Value arrayTmp = Value.asValue(excludeColumnFiledNames);
            if (arrayTmp.hasArrayElements()) {
                for (int i = 0; i < arrayTmp.getArraySize(); i++) {
                    Value item = arrayTmp.getArrayElement(i);
                    if (item.isString()) {
                        config.getIncludeColumnFiledNames().add(item.asString());
                    }
                }
            }
        }
        Object includeColumnIndexes = configMap.get("includeColumnIndexes");
        if (includeColumnIndexes instanceof Map) {
            Value arrayTmp = Value.asValue(includeColumnIndexes);
            if (arrayTmp.hasArrayElements()) {
                for (int i = 0; i < arrayTmp.getArraySize(); i++) {
                    Value item = arrayTmp.getArrayElement(i);
                    if (item.isNumber()) {
                        config.getIncludeColumnIndexes().add(item.asInt());
                    }
                }
            }
        }
        Object needHead = configMap.get("needHead");
        if (needHead instanceof Boolean) {
            config.setNeedHead((Boolean) needHead);
        }
        Object relativeHeadRowIndex = configMap.get("relativeHeadRowIndex");
        if (relativeHeadRowIndex instanceof Number) {
            config.setRelativeHeadRowIndex(((Number) relativeHeadRowIndex).intValue());
        }
        Object useDefaultStyle = configMap.get("useDefaultStyle");
        if (useDefaultStyle instanceof Boolean) {
            config.setUseDefaultStyle((Boolean) useDefaultStyle);
        }
        Object excelType = configMap.get("excelType");
        if (excelType instanceof String) {
            config.setExcelType(toExcelTypeEnum((String) excelType));
        }
        Object password = configMap.get("password");
        if (password instanceof String) {
            config.setPassword((String) password);
        }
        Object sheetNo = configMap.get("sheetNo");
        if (sheetNo instanceof Number) {
            config.setSheetNo(((Number) sheetNo).intValue());
        }
        Object sheetName = configMap.get("sheetName");
        if (sheetName instanceof String) {
            config.setSheetName((String) sheetName);
        }
        Object use1904windowing = configMap.get("use1904windowing");
        if (use1904windowing instanceof Boolean) {
            config.setUse1904windowing((Boolean) use1904windowing);
        }
        Object locale = configMap.get("locale");
        if (locale instanceof String) {
            config.setLocale(toLocale((String) locale));
        }
        Object autoTrim = configMap.get("autoTrim");
        if (autoTrim instanceof Boolean) {
            config.setAutoTrim((Boolean) autoTrim);
        }
        Object columns = configMap.get("columns");
        if (columns instanceof Map) {
            Map<String, Object> columnsMap = (Map<String, Object>) columns;
            for (Map.Entry<String, Object> entry : columnsMap.entrySet()) {
                String columnName = entry.getKey();
                Object columnConfig = entry.getValue();
                if (StringUtils.isBlank(columnName) || !(columnConfig instanceof Map)) {
                    continue;
                }
                config.getColumns().put(columnName, toExcelWriterHeadConfig((Map<String, Object>) columnConfig));
            }
        }
        Object styleConfig = configMap.get("styleConfig");
        if (styleConfig instanceof Map) {
            toWriterStyleConfig((Map<String, Object>) styleConfig, config.getStyleConfig());
        }
        return config;
    }

    private static Class<?> getClass(String dataType) {
        if (StringUtils.isBlank(dataType)) {
            return null;
        }
        switch (dataType) {
            case "JString":
                return String.class;
            case "JBigDecimal":
                return BigDecimal.class;
            case "JBoolean":
                return Boolean.class;
            case "JDate":
                break;
            case "JInteger":
                return Integer.class;
            case "JDouble":
                return Double.class;
            case "JLong":
                return Long.class;
            case "JFloat":
                return Float.class;
            case "JShort":
                return Short.class;
            case "JByte":
                return Byte.class;
            case "JByte[]":
                return Byte[].class;
        }
        return null;
    }

    private static ExcelTypeEnum toExcelTypeEnum(String excelType) {
        if (StringUtils.isBlank(excelType)) {
            return null;
        }
        switch (excelType) {
            case "XLSX":
                return ExcelTypeEnum.XLSX;
            case "XLS":
                return ExcelTypeEnum.XLS;
            default:
                return ExcelTypeEnum.valueOf(excelType);
        }
    }

    private static CellExtraTypeEnum toCellExtraTypeEnum(String extraRead) {
        if (StringUtils.isBlank(extraRead)) {
            return null;
        }
        switch (extraRead) {
            case "COMMENT":
                return CellExtraTypeEnum.COMMENT;
            case "HYPERLINK":
                return CellExtraTypeEnum.HYPERLINK;
            case "MERGE":
                return CellExtraTypeEnum.MERGE;
            default:
                return CellExtraTypeEnum.valueOf(extraRead);
        }
    }

    private static HorizontalAlignment toHorizontalAlignment(String horizontalAlignment) {
        if (StringUtils.isBlank(horizontalAlignment)) {
            return null;
        }
        switch (horizontalAlignment) {
            case "GENERAL":
                return HorizontalAlignment.GENERAL;
            case "LEFT":
                return HorizontalAlignment.LEFT;
            case "CENTER":
                return HorizontalAlignment.CENTER;
            case "RIGHT":
                return HorizontalAlignment.RIGHT;
            case "FILL":
                return HorizontalAlignment.FILL;
            case "JUSTIFY":
                return HorizontalAlignment.JUSTIFY;
            case "CENTER_SELECTION":
                return HorizontalAlignment.CENTER_SELECTION;
            case "DISTRIBUTED":
                return HorizontalAlignment.DISTRIBUTED;
            default:
                return HorizontalAlignment.valueOf(horizontalAlignment);
        }
    }

    private static VerticalAlignment toVerticalAlignment(String verticalAlignment) {
        if (StringUtils.isBlank(verticalAlignment)) {
            return null;
        }
        switch (verticalAlignment) {
            case "TOP":
                return VerticalAlignment.TOP;
            case "CENTER":
                return VerticalAlignment.CENTER;
            case "BOTTOM":
                return VerticalAlignment.BOTTOM;
            case "JUSTIFY":
                return VerticalAlignment.JUSTIFY;
            case "DISTRIBUTED":
                return VerticalAlignment.DISTRIBUTED;
            default:
                return VerticalAlignment.valueOf(verticalAlignment);
        }
    }

    public static Locale toLocale(String locale) {
        if (StringUtils.isBlank(locale)) {
            return null;
        }
        switch (locale) {
            case "ENGLISH":
                return Locale.ENGLISH;
            case "CHINESE":
                return Locale.CHINESE;
            case "SIMPLIFIED_CHINESE":
                return Locale.SIMPLIFIED_CHINESE;
            case "TRADITIONAL_CHINESE":
                return Locale.TRADITIONAL_CHINESE;
        }
        return Locale.SIMPLIFIED_CHINESE;
    }

    private static void toWriterStyleConfig(Map<String, Object> column, org.clever.hinny.core.ExcelUtils.WriterStyleConfig writerStyleConfig) {
        toContentRowHeight(column, writerStyleConfig.getContentRowHeight());
        toContentFontStyle(column, writerStyleConfig.getContentFontStyle());
        toContentStyle(column, writerStyleConfig.getContentStyle());
        toHeadRowHeight(column, writerStyleConfig.getHeadRowHeight());
        toHeadFontStyle(column, writerStyleConfig.getHeadFontStyle());
        toHeadStyle(column, writerStyleConfig.getHeadStyle());
        toOnceAbsoluteMerge(column, writerStyleConfig.getOnceAbsoluteMerge());
    }

    private static org.clever.hinny.core.ExcelUtils.ExcelWriterHeadConfig toExcelWriterHeadConfig(Map<String, Object> column) {
        org.clever.hinny.core.ExcelUtils.ExcelWriterHeadConfig headConfig = new org.clever.hinny.core.ExcelUtils.ExcelWriterHeadConfig();
        toExcelProperty(column, headConfig.getExcelProperty());
        toDateTimeFormat(column, headConfig.getDateTimeFormat());
        toNumberFormat(column, headConfig.getNumberFormat());
        toColumnWidth(column, headConfig.getColumnWidth());
        toContentFontStyle(column, headConfig.getContentFontStyle());
        toContentLoopMerge(column, headConfig.getContentLoopMerge());
        toContentStyle(column, headConfig.getContentStyle());
        toHeadFontStyle(column, headConfig.getHeadFontStyle());
        toHeadStyle(column, headConfig.getHeadStyle());
        return headConfig;
    }

    private static org.clever.hinny.core.ExcelUtils.ExcelReaderHeadConfig toExcelReaderHeadConfig(Map<String, Object> column) {
        org.clever.hinny.core.ExcelUtils.ExcelReaderHeadConfig headConfig = new org.clever.hinny.core.ExcelUtils.ExcelReaderHeadConfig();
        Object dataType = column.get("dataType");
        if (dataType instanceof String) {
            headConfig.setDataType(getClass((String) dataType));
        }
        toExcelProperty(column, headConfig.getExcelProperty());
        toDateTimeFormat(column, headConfig.getDateTimeFormat());
        toNumberFormat(column, headConfig.getNumberFormat());
        return headConfig;
    }

    private static void toExcelProperty(Map<String, Object> column, org.clever.hinny.core.ExcelUtils.ExcelProperty excelProperty) {
        Object columnV = column.get("column");
        if (columnV instanceof String) {
            excelProperty.getColumn().add((String) columnV);
        } else if (columnV instanceof Map) {
            Value arrayTmp = Value.asValue(columnV);
            if (arrayTmp.hasArrayElements()) {
                for (int i = 0; i < arrayTmp.getArraySize(); i++) {
                    Value item = arrayTmp.getArrayElement(i);
                    if (item.isString()) {
                        excelProperty.getColumn().add(item.asString());
                    }
                }
            }
        }
        Object ignore = column.get("ignore");
        if (ignore instanceof Boolean) {
            excelProperty.setIgnore((Boolean) ignore);
        }
        Object index = column.get("index");
        if (index instanceof Number) {
            excelProperty.setIndex(((Number) index).intValue());
        }
    }

    private static void toDateTimeFormat(Map<String, Object> column, org.clever.hinny.core.ExcelUtils.DateTimeFormat dateTimeFormat) {
        Object dateFormat = column.get("dateFormat");
        if (dateFormat instanceof String) {
            dateTimeFormat.setDateFormat((String) dateFormat);
        }
        Object use1904windowing = column.get("use1904windowing");
        if (use1904windowing instanceof Boolean) {
            dateTimeFormat.setUse1904windowing((Boolean) use1904windowing);
        }
    }

    private static void toNumberFormat(Map<String, Object> column, org.clever.hinny.core.ExcelUtils.NumberFormat numberFormat) {
        Object numberFormatV = column.get("numberFormat");
        if (numberFormatV instanceof String) {
            numberFormat.setNumberFormat((String) numberFormatV);
        }
        Object roundingMode = column.get("roundingMode");
        if (roundingMode instanceof Number) {
            numberFormat.setRoundingMode(RoundingMode.valueOf(((Number) roundingMode).intValue()));
        }
    }

    private static void toColumnWidth(Map<String, Object> column, org.clever.hinny.core.ExcelUtils.ColumnWidth columnWidth) {
        Object columnWidthV = column.get("columnWidth");
        if (columnWidthV instanceof Number) {
            columnWidth.setColumnWidth(((Number) columnWidthV).intValue());
        }
    }

    private static void toExcelFontStyle(Map<String, Object> column, org.clever.hinny.core.ExcelUtils.ExcelFontStyle excelFontStyle) {
        Object fontName = column.get("fontName");
        if (fontName instanceof String) {
            excelFontStyle.setFontName((String) fontName);
        }
        Object fontHeightInPoints = column.get("fontHeightInPoints");
        if (fontHeightInPoints instanceof Number) {
            excelFontStyle.setFontHeightInPoints(((Number) fontHeightInPoints).shortValue());
        }
        Object italic = column.get("italic");
        if (italic instanceof Boolean) {
            excelFontStyle.setItalic((Boolean) italic);
        }
        Object strikeout = column.get("strikeout");
        if (strikeout instanceof Boolean) {
            excelFontStyle.setStrikeout((Boolean) strikeout);
        }
        Object color = column.get("color");
        if (color instanceof Number) {
            excelFontStyle.setColor(((Number) color).shortValue());
        }
        Object typeOffset = column.get("typeOffset");
        if (typeOffset instanceof Number) {
            excelFontStyle.setTypeOffset(((Number) typeOffset).shortValue());
        }
        Object underline = column.get("underline");
        if (underline instanceof Number) {
            excelFontStyle.setUnderline(((Number) underline).byteValue());
        }
        Object charset = column.get("charset");
        if (charset instanceof Number) {
            excelFontStyle.setCharset(((Number) charset).intValue());
        }
        Object bold = column.get("bold");
        if (bold instanceof Boolean) {
            excelFontStyle.setBold((Boolean) bold);
        }
    }

    private static void toContentFontStyle(Map<String, Object> column, org.clever.hinny.core.ExcelUtils.ContentFontStyle contentFontStyle) {
        toExcelFontStyle(column, contentFontStyle);
    }

    private static void toContentLoopMerge(Map<String, Object> column, org.clever.hinny.core.ExcelUtils.ContentLoopMerge contentLoopMerge) {
        Object eachRow = column.get("eachRow");
        if (eachRow instanceof Number) {
            contentLoopMerge.setEachRow(((Number) eachRow).intValue());
        }
        Object columnExtend = column.get("columnExtend");
        if (columnExtend instanceof Number) {
            contentLoopMerge.setColumnExtend(((Number) columnExtend).intValue());
        }
    }

    private static void toContentRowHeight(Map<String, Object> column, org.clever.hinny.core.ExcelUtils.ContentRowHeight contentRowHeight) {
        Object rowHeight = column.get("rowHeight");
        if (rowHeight instanceof Number) {
            contentRowHeight.setRowHeight(((Number) rowHeight).shortValue());
        }
    }

    private static void toExcelCellStyle(Map<String, Object> column, org.clever.hinny.core.ExcelUtils.ExcelCellStyle excelCellStyle) {
        Object dataFormat = column.get("dataFormat");
        if (dataFormat instanceof Number) {
            excelCellStyle.setDataFormat(((Number) dataFormat).shortValue());
        }
        Object hidden = column.get("hidden");
        if (hidden instanceof Boolean) {
            excelCellStyle.setHidden(((Boolean) hidden));
        }
        Object locked = column.get("locked");
        if (locked instanceof Boolean) {
            excelCellStyle.setLocked(((Boolean) locked));
        }
        Object quotePrefix = column.get("quotePrefix");
        if (quotePrefix instanceof Boolean) {
            excelCellStyle.setQuotePrefix(((Boolean) quotePrefix));
        }
        Object horizontalAlignment = column.get("horizontalAlignment");
        if (horizontalAlignment instanceof String) {
            excelCellStyle.setHorizontalAlignment(toHorizontalAlignment((String) horizontalAlignment));
        }
        Object wrapped = column.get("wrapped");
        if (wrapped instanceof Boolean) {
            excelCellStyle.setWrapped(((Boolean) wrapped));
        }
        Object verticalAlignment = column.get("verticalAlignment");
        if (verticalAlignment instanceof String) {
            excelCellStyle.setVerticalAlignment(toVerticalAlignment((String) verticalAlignment));
        }
        Object rotation = column.get("rotation");
        if (rotation instanceof Number) {
            excelCellStyle.setRotation(((Number) rotation).shortValue());
        }
        Object indent = column.get("indent");
        if (indent instanceof Number) {
            excelCellStyle.setIndent(((Number) indent).shortValue());
        }
        Object borderLeft = column.get("borderLeft");
        if (borderLeft instanceof Number) {
            excelCellStyle.setBorderLeft(BorderStyle.valueOf(((Number) borderLeft).shortValue()));
        }
        Object borderRight = column.get("borderRight");
        if (borderRight instanceof Number) {
            excelCellStyle.setBorderRight(BorderStyle.valueOf(((Number) borderRight).shortValue()));
        }
        Object borderTop = column.get("borderTop");
        if (borderTop instanceof Number) {
            excelCellStyle.setBorderTop(BorderStyle.valueOf(((Number) borderTop).shortValue()));
        }
        Object borderBottom = column.get("borderBottom");
        if (borderBottom instanceof Number) {
            excelCellStyle.setBorderBottom(BorderStyle.valueOf(((Number) borderBottom).shortValue()));
        }
        Object leftBorderColor = column.get("leftBorderColor");
        if (leftBorderColor instanceof Number) {
            excelCellStyle.setLeftBorderColor(((Number) leftBorderColor).shortValue());
        }
        Object rightBorderColor = column.get("rightBorderColor");
        if (rightBorderColor instanceof Number) {
            excelCellStyle.setRightBorderColor(((Number) rightBorderColor).shortValue());
        }
        Object topBorderColor = column.get("topBorderColor");
        if (topBorderColor instanceof Number) {
            excelCellStyle.setTopBorderColor(((Number) topBorderColor).shortValue());
        }
        Object bottomBorderColor = column.get("bottomBorderColor");
        if (bottomBorderColor instanceof Number) {
            excelCellStyle.setBottomBorderColor(((Number) bottomBorderColor).shortValue());
        }
        Object fillPatternType = column.get("fillPatternType");
        if (fillPatternType instanceof Number) {
            excelCellStyle.setFillPatternType(FillPatternType.forInt(((Number) fillPatternType).intValue()));
        }
        Object fillBackgroundColor = column.get("fillBackgroundColor");
        if (fillBackgroundColor instanceof Number) {
            excelCellStyle.setFillBackgroundColor(((Number) fillBackgroundColor).shortValue());
        }
        Object fillForegroundColor = column.get("fillForegroundColor");
        if (fillForegroundColor instanceof Number) {
            excelCellStyle.setFillForegroundColor(((Number) fillForegroundColor).shortValue());
        }
        Object shrinkToFit = column.get("shrinkToFit");
        if (shrinkToFit instanceof Boolean) {
            excelCellStyle.setShrinkToFit((Boolean) shrinkToFit);
        }
    }

    private static void toContentStyle(Map<String, Object> column, org.clever.hinny.core.ExcelUtils.ContentStyle contentStyle) {
        toExcelCellStyle(column, contentStyle);
    }

    private static void toHeadFontStyle(Map<String, Object> column, org.clever.hinny.core.ExcelUtils.HeadFontStyle headFontStyle) {
        toExcelFontStyle(column, headFontStyle);
    }

    private static void toHeadStyle(Map<String, Object> column, org.clever.hinny.core.ExcelUtils.HeadStyle headStyle) {
        toExcelCellStyle(column, headStyle);
    }

    private static void toHeadRowHeight(Map<String, Object> column, org.clever.hinny.core.ExcelUtils.HeadRowHeight headRowHeight) {
        Object headRowHeightV = column.get("headRowHeight");
        if (headRowHeightV instanceof Number) {
            headRowHeight.setHeadRowHeight(((Number) headRowHeightV).shortValue());
        }
    }

    private static void toOnceAbsoluteMerge(Map<String, Object> column, org.clever.hinny.core.ExcelUtils.OnceAbsoluteMerge onceAbsoluteMerge) {
        Object firstRowIndex = column.get("firstRowIndex");
        if (firstRowIndex instanceof Number) {
            onceAbsoluteMerge.setFirstRowIndex(((Number) firstRowIndex).intValue());
        }
        Object lastRowIndex = column.get("lastRowIndex");
        if (lastRowIndex instanceof Number) {
            onceAbsoluteMerge.setLastRowIndex(((Number) lastRowIndex).intValue());
        }
        Object firstColumnIndex = column.get("firstColumnIndex");
        if (firstColumnIndex instanceof Number) {
            onceAbsoluteMerge.setFirstColumnIndex(((Number) firstColumnIndex).intValue());
        }
        Object lastColumnIndex = column.get("lastColumnIndex");
        if (lastColumnIndex instanceof Number) {
            onceAbsoluteMerge.setLastColumnIndex(((Number) lastColumnIndex).intValue());
        }
    }
}
