/**
 * Project Name : Business Tool<br>
 * File Name : DtoTemplateReplacementSequenceManager.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/18<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */
package org.thinkit.generator.rule.dtogenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.common.rule.AbstractRule;
import org.thinkit.common.rule.Attribute;
import org.thinkit.common.rule.Content;
import org.thinkit.common.rule.exception.RuleHandlingException;
import org.thinkit.generator.catalog.dtogenerator.DtoTemplateReplacementSequence;

import com.google.common.flogger.FluentLogger;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * コンテンツからDTO雛形置換文字列情報を取得する処理を行うルールです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class DtoTemplateReplacementSequenceManager extends AbstractRule {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * DTO雛形置換文字列リスト
     */
    @Getter(AccessLevel.PRIVATE)
    private List<Map<String, String>> dtoTemplateReplacementSequences = new ArrayList<>(0);

    /**
     * デフォルトコンストラクタ
     */
    public DtoTemplateReplacementSequenceManager() {
        super.loadContent(ContentName.DTO雛形置換文字列情報);
    }

    /**
     * コンテンツ名定数
     */
    private enum ContentName implements Content {
        DTO雛形置換文字列情報;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * コンテンツ要素定数
     */
    public enum ContentAttribute implements Attribute {
        置換文字列コード, 置換文字列;

        @Override
        public String getString() {
            return this.name();
        }
    }

    @Override
    public boolean execute() {
        logger.atInfo().log("START");

        final List<Map<String, String>> contents = super.getContents();

        if (contents.isEmpty()) {
            logger.atSevere().log("DTO雛形置換文字列情報をコンテンツから取得できませんでした。");
            return false;
        }

        this.dtoTemplateReplacementSequences = contents;

        logger.atInfo().log("取得したDTO雛形置換文字列情報 = (%s)", contents);
        logger.atInfo().log("END");
        return true;
    }

    @Override
    protected List<Attribute> getAttributes() {
        logger.atInfo().log("START");

        final List<Attribute> attributes = new ArrayList<>(2);
        attributes.add(ContentAttribute.置換文字列コード);
        attributes.add(ContentAttribute.置換文字列);

        logger.atInfo().log("DTO雛形置換文字列情報のアトリビュート = (%s)", attributes);
        logger.atInfo().log("END");
        return attributes;
    }

    /**
     * コンテンツファイルからカタログのコード値と紐づく雛形の置換文字列を取得し返却します。<br>
     * カタログのコード値と紐づくコード値がコンテンツファイルに定義されていない場合、<br>
     * または該当レコードの雛形の定義に誤りがある場合は実行時に失敗します。
     *
     * @param dtoTemplate DTO雛形置換文字列区分
     * @return DTO雛形置換文字列区分のコード値に紐づくレコードに定義された置換文字列
     * @throws RuleHandlingException カタログのコード値と紐づくレコードがコンテンツファイルに存在しない場合、<br>
     *                               または取得したレコード内の置換文字列がnullまたは空文字列の場合
     */
    public String getTemplateByKey(DtoTemplateReplacementSequence dtoTemplateReplacementSequence) {
        logger.atInfo().log("START");
        Objects.requireNonNull(dtoTemplateReplacementSequence, "DtoTemplateReplacementSequence must not be null.");

        final Map<String, String> record = super.getRecordByKey(ContentAttribute.置換文字列コード,
                dtoTemplateReplacementSequence);

        if (record.isEmpty()) {
            throw new RuleHandlingException(
                    "There is no record defined in the content file that is associated with a code value in the catalog.");
        }

        final String template = record.get(ContentAttribute.置換文字列.name());

        if (StringUtils.isEmpty(template)) {
            throw new RuleHandlingException("The template retrieved from the content file is a null or empty.");
        }

        logger.atInfo().log("取得した雛形の置換文字列 = (%s)", template);
        logger.atInfo().log("END");
        return template;
    }
}
