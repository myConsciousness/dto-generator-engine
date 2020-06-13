/**
 * Project Name : Generator<br>
 * File Name : ClassNameDefinitionManager.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/16<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.dtogenerator;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.google.common.flogger.FluentLogger;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.common.catalog.Catalog;
import org.thinkit.common.rule.AbstractRule;
import org.thinkit.common.rule.Attribute;
import org.thinkit.common.rule.Content;
import org.thinkit.common.util.ExcelHandler;
import org.thinkit.common.util.ExcelHandler.QueueType;
import org.thinkit.generator.catalog.dtogenerator.DtoCellItem;
import org.thinkit.generator.dtogenerator.ClassNameDefinition;
import org.thinkit.generator.rule.Sheet;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Excelに記述された定義書シートからクラス名定義情報を抽出する処理を行うルールです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class ClassNameDefinitionManager extends AbstractRule {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * SheetHandlerオブジェクト
     */
    private ExcelHandler.SheetHandler sheetHandler = null;

    /**
     * ファイルパス
     */
    private String filePath = "";

    /**
     * クラス名定義情報
     */
    @Getter
    private ClassNameDefinition classNameDefinition = null;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private ClassNameDefinitionManager() {
    }

    /**
     * コンストラクタ
     *
     * @param filePath DTO定義書のファイルパス
     * @exception IllegalArgumentException ファイルパスがnullまたは空文字列の場合
     */
    public ClassNameDefinitionManager(String filePath) {
        logger.atInfo().log("ファイルパス = (%s)", filePath);

        if (StringUtils.isEmpty(filePath)) {
            logger.atInfo().log("初期化処理でエラーが発生しました。");
            logger.atInfo().log("ファイルパスがnullまたは空文字列です。");
            throw new IllegalArgumentException("wrong parameter was given. File path is required.");
        }

        this.filePath = filePath;

        super.loadContent(ContentName.クラス名定義情報);
    }

    /**
     * コンストラクタ
     *
     * @param sheetHandler DTO定義書の情報を持つSheetHandlerオブジェクト
     */
    public ClassNameDefinitionManager(ExcelHandler.SheetHandler sheetHandler) {
        Objects.requireNonNull(sheetHandler, "wrong parameter was given. Object is null.");
        this.sheetHandler = sheetHandler;

        super.loadContent(ContentName.クラス名定義情報);
    }

    /**
     * シート名定数
     */
    private enum SheetName implements Sheet {
        定義書;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * コンテンツ名定数
     */
    private enum ContentName implements Content {
        クラス名定義情報;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * コンテンツ要素定数
     */
    private enum ContentAttribute implements Attribute {
        セル項目コード, セル項目名;

        @Override
        public String getString() {
            return this.name();
        }
    }

    @Override
    public boolean execute() {
        logger.atInfo().log("START");

        if (this.sheetHandler == null) {
            final ExcelHandler excelHandler = new ExcelHandler.Builder().fromFile(this.filePath).build();
            this.sheetHandler = excelHandler.sheet(SheetName.定義書.name());
        }

        final Map<DtoCellItem, String> definitions = this.getNameDefinitions(sheetHandler);
        final ClassNameDefinition classNameDefinition = new ClassNameDefinition(definitions.get(DtoCellItem.VERSION),
                definitions.get(DtoCellItem.PROJECT_NAME), definitions.get(DtoCellItem.PACKAGE_NAME),
                definitions.get(DtoCellItem.PHYSICAL_NAME), definitions.get(DtoCellItem.LOGICAL_NAME),
                definitions.get(DtoCellItem.DESCRIPTION));

        this.classNameDefinition = classNameDefinition;

        logger.atInfo().log("クラス名定義情報 = (%s)", classNameDefinition);
        logger.atInfo().log("END");
        return true;
    }

    /**
     * セル内に定義された作成者情報を取得し返却します。
     *
     * @param sheetHandler SheetHandlerオブジェクト
     * @return セルに定義されたクラス名情報
     */
    private EnumMap<DtoCellItem, String> getNameDefinitions(ExcelHandler.SheetHandler sheetHandler) {
        logger.atInfo().log("START");

        final List<Map<String, String>> contents = super.getContents();
        final EnumMap<DtoCellItem, String> classNameDefinitions = new EnumMap<>(DtoCellItem.class);

        for (Map<String, String> elements : contents) {
            final String cellItemName = elements.get(ContentAttribute.セル項目名.name());
            final Map<QueueType, Integer> baseIndexes = sheetHandler.findCellIndex(cellItemName);

            final int baseStartColumnIndex = baseIndexes.get(QueueType.COLUMN);
            final int baseStartRowIndex = baseIndexes.get(QueueType.ROW);
            logger.atInfo().log("基準開始列インデックス = (%s)", baseStartColumnIndex);
            logger.atInfo().log("基準開始行インデックス = (%s)", baseStartRowIndex);

            final String sequence = sheetHandler.getRegionSequence(baseStartColumnIndex, baseStartRowIndex);
            logger.atInfo().log("取得した領域内の値 = (%s)", sequence);

            final int itemCode = Integer.parseInt(elements.get(ContentAttribute.セル項目コード.name()));
            classNameDefinitions.put(Catalog.getEnum(DtoCellItem.class, itemCode), sequence);
        }

        logger.atInfo().log("コンテンツ情報 = (%s)", classNameDefinitions);
        logger.atInfo().log("END");
        return classNameDefinitions;
    }

    @Override
    protected List<Attribute> getAttributes() {
        logger.atInfo().log("START");

        final List<Attribute> attributes = new ArrayList<>(2);
        attributes.add(ContentAttribute.セル項目コード);
        attributes.add(ContentAttribute.セル項目名);

        logger.atInfo().log("クラス名定義情報のアトリビュート = (%s)", attributes);
        logger.atInfo().log("END");
        return attributes;
    }
}
