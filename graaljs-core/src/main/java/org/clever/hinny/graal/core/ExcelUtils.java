package org.clever.hinny.graal.core;

import org.clever.common.utils.excel.ExcelDataReader;
import org.clever.common.utils.excel.ExcelDataWriter;
import org.clever.common.utils.excel.dto.ExcelData;
import org.graalvm.polyglot.Value;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

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

    @SuppressWarnings("rawtypes")
    public Map<String, ExcelData<Map>> read(org.clever.hinny.core.ExcelUtils.ExcelDataReaderConfig config) {
        return null;
    }

    @SuppressWarnings("rawtypes")
    public void write(org.clever.hinny.core.ExcelUtils.ExcelDataWriterConfig config, List<Map> listData) {

    }

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
        if (excelReaderExceptionHand instanceof Value && ((Value) excelReaderExceptionHand).isException()) {
            // TODO ????
            // config.setExcelReaderExceptionHand((Value) excelReaderExceptionHand);
        }
        Object excelRowReader = configMap.get("excelRowReader");
        if (excelRowReader instanceof Value && ((Value) excelRowReader).isException()) {
            // TODO ????
            // config.setExcelRowReader((Value) excelRowReader);
        }
        Object autoCloseStream = configMap.get("autoCloseStream");
        if (autoCloseStream instanceof Boolean) {
            config.setAutoCloseStream((Boolean) autoCloseStream);
        }
        Object extraRead = configMap.get("extraRead");
        if (extraRead instanceof Value && ((Value) extraRead).hasArrayElements()) {
            Value arrayTmp = (Value) extraRead;
            for (int i = 0; i < arrayTmp.getArraySize(); i++) {
                Value item = arrayTmp.getArrayElement(i);
                if (item.isString()) {
                    // TODO ???
                }
            }
            // config.setExtraRead((InputStream) extraRead);
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
            // TODO ???
            // config.setLocale((String) locale);
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
        if (columns instanceof Value) {
            // TODO ???
            Value columnsValue = (Value) columns;
            config.getColumns().put("", null);
        }
        return config;
    }

    private static org.clever.hinny.core.ExcelUtils.ExcelDataWriterConfig toExcelDataWriterConfig(Map<String, Object> configMap) {
        org.clever.hinny.core.ExcelUtils.ExcelDataWriterConfig config = new org.clever.hinny.core.ExcelUtils.ExcelDataWriterConfig();

        return config;
    }
}
