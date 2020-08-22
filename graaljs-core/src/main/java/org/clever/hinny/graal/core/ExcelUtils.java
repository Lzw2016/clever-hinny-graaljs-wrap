package org.clever.hinny.graal.core;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.enums.CellExtraTypeEnum;
import org.apache.commons.lang3.StringUtils;
import org.clever.common.utils.excel.ExcelDataReader;
import org.clever.common.utils.excel.ExcelDataWriter;
import org.clever.common.utils.excel.ExcelRowReader;
import org.clever.common.utils.excel.dto.ExcelData;
import org.clever.common.utils.excel.dto.ExcelRow;
import org.graalvm.polyglot.Value;

import java.io.InputStream;
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
    public ExcelDataReader<Map> createReader(org.clever.hinny.core.ExcelUtils.ExcelDataReaderConfig config) {
        return null;
    }

    public ExcelDataWriter createWriter(org.clever.hinny.core.ExcelUtils.ExcelDataWriterConfig config) {
        return null;
    }

    @SuppressWarnings({"rawtypes"})
    public Map<String, ExcelData<Map>> read(Map<String, Object> configMap) {
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
            return excelDataReader.getExcelSheetMap();
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public void write(org.clever.hinny.core.ExcelUtils.ExcelDataWriterConfig config, List<Map> listData) {

    }

    @SuppressWarnings({"unchecked", "rawtypes"})
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
        if (limitRows instanceof Integer) {
            config.setLimitRows((Integer) limitRows);
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
        if (sheetNo instanceof Integer) {
            config.setSheetNo((Integer) sheetNo);
        }
        Object sheetName = configMap.get("sheetName");
        if (sheetName instanceof String) {
            config.setSheetName((String) sheetName);
        }
        Object headRowNumber = configMap.get("headRowNumber");
        if (headRowNumber instanceof Integer) {
            config.setHeadRowNumber((Integer) headRowNumber);
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

    private static org.clever.hinny.core.ExcelUtils.ExcelDataWriterConfig toExcelDataWriterConfig(Map<String, Object> configMap) {
        org.clever.hinny.core.ExcelUtils.ExcelDataWriterConfig config = new org.clever.hinny.core.ExcelUtils.ExcelDataWriterConfig();

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
        if (index instanceof Integer) {
            excelProperty.setIndex((Integer) index);
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
        if (roundingMode instanceof Integer) {
            numberFormat.setRoundingMode(RoundingMode.valueOf((Integer) roundingMode));
        }
    }
}
