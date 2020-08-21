package org.clever.hinny.graal.core;

import org.clever.common.utils.excel.ExcelDataReader;
import org.clever.common.utils.excel.ExcelDataWriter;
import org.clever.common.utils.excel.dto.ExcelData;

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

        return config;
    }

    private static org.clever.hinny.core.ExcelUtils.ExcelDataWriterConfig toExcelDataWriterConfig(Map<String, Object> configMap) {
        org.clever.hinny.core.ExcelUtils.ExcelDataWriterConfig config = new org.clever.hinny.core.ExcelUtils.ExcelDataWriterConfig();

        return config;
    }
}
