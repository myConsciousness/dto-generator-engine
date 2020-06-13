package org.thinkit.generator.rule.dtogenerator;

import java.util.HashMap;
import java.util.Map;

import com.google.common.flogger.FluentLogger;

import lombok.NonNull;

public class DtoClassResourceFacade {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * デフォルトコンストラクタ
     */
    private DtoClassResourceFacade() {
    }

    public static Map<String, String> createResource(@NonNull String filePath) {
        logger.atInfo().log("START");

        final ClassDefinitionMatrixManager classDefinitionMatrixManager = new ClassDefinitionMatrixManager(filePath);

        if (!classDefinitionMatrixManager.execute()) {
            logger.atSevere().log("クラス定義情報群の生成処理が異常終了しました。");
            return new HashMap<>();
        }

        final ClassDefinitionMatrixFormatter classDefinitionMatrixFormatter = new ClassDefinitionMatrixFormatter(
                classDefinitionMatrixManager.getClassDefinitionMatrix());

        if (!classDefinitionMatrixFormatter.execute()) {
            logger.atSevere().log("クラス定義情報の整形処理が異常終了しました。");
            return new HashMap<>();
        }

        logger.atInfo().log("END");
        return classDefinitionMatrixFormatter.getFormattedResources();
    }
}