/**
 * Project Name : Generator<br>
 * File Name : DtoGenerator.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/04/21<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.dtogenerator;

import com.google.common.flogger.FluentLogger;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.common.catalog.Extension;
import org.thinkit.common.util.FileHandler;
import org.thinkit.generator.AbstractGenerator;
import org.thinkit.generator.DefinitionPath;
import org.thinkit.generator.rule.dtogenerator.DtoClassResourceFacade;

import lombok.NonNull;

/**
 * DTO定義書を解析してDTOクラスを生成する処理を定義したクラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class DtoGenerator extends AbstractGenerator {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * コンストラクタ
     *
     * @param definitionPath 生成するパスを管理するオブジェクト
     */
    public DtoGenerator(@NonNull DefinitionPath definitionPath) {
        super(definitionPath);
    }

    @Override
    protected boolean run() {
        logger.atInfo().log("START");

        final DtoClassResource dtoClassResource = DtoClassResourceFacade.createResource(super.getFilePath());

        if (dtoClassResource == null) {
            logger.atSevere().log("DTOクラスのリソース作成処理が異常終了しました。");
            return false;
        }

        final String outputPath = super.getOutputPath(dtoClassResource.getPackageName());

        if (StringUtils.isEmpty(outputPath)) {
            logger.atSevere().log("出力先パスの取得処理が異常終了しました。");
            return false;
        }

        final FileHandler fileHandler = new FileHandler(outputPath);

        dtoClassResource.getResources().forEach((key, value) -> {
            fileHandler.write(key, Extension.java(), value);
        });

        logger.atInfo().log("END");
        return true;
    }
}
