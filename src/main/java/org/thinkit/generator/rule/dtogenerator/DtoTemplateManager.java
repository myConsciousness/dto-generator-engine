/**
 * Project Name : Generator<br>
 * File Name : DtoTemplateManager.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/17<br>
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
import org.thinkit.generator.catalog.dtogenerator.DtoTemplate;

import com.google.common.flogger.FluentLogger;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * コンテンツからDTO雛形情報を取得する処理を行うルールです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class DtoTemplateManager extends AbstractRule {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * デフォルトコンストラクタ
     */
    public DtoTemplateManager() {
        super.loadContent(ContentName.DTO雛形情報);
    }

    /**
     * コンテンツ名定数
     */
    private enum ContentName implements Content {
        DTO雛形情報;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * コンテンツ要素定数
     */
    public enum ContentAttribute implements Attribute {
        雛形コード, 雛形;

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
            logger.atSevere().log("DTO雛形情報をコンテンツから取得できませんでした。");
            return false;
        }

        logger.atInfo().log("END");
        return true;
    }

    @Override
    protected List<Attribute> getAttributes() {
        logger.atInfo().log("START");

        final List<Attribute> attributes = new ArrayList<>(2);
        attributes.add(ContentAttribute.雛形コード);
        attributes.add(ContentAttribute.雛形);

        logger.atInfo().log("DTO雛形情報のアトリビュート = (%s)", attributes);
        logger.atInfo().log("END");
        return attributes;
    }

    /**
     * コンテンツファイルからカタログのコード値と紐づく雛形を取得し返却します。<br>
     * カタログのコード値と紐づくコード値がコンテンツファイルに定義されていない場合、<br>
     * または該当レコードの雛形の定義に誤りがある場合は実行時に失敗します。
     *
     * @param dtoTemplate DTO雛形区分
     * @return DTO雛形区分のコード値に紐づくレコードに定義された雛形
     * @throws RuleHandlingException カタログのコード値と紐づくレコードがコンテンツファイルに存在しない場合、<br>
     *                               または取得したレコード内の雛形がnullまたは空文字列の場合
     */
    public String getTemplateByKey(DtoTemplate dtoTemplate) {
        logger.atInfo().log("START");
        Objects.requireNonNull(dtoTemplate, "DtoTemplate must not be null.");

        final Map<String, String> record = super.getRecordByKey(ContentAttribute.雛形コード, dtoTemplate);

        if (record.isEmpty()) {
            throw new RuleHandlingException(
                    "There is no record defined in the content file that is associated with a code value in the catalog.");
        }

        final String template = record.get(ContentAttribute.雛形.name());

        if (StringUtils.isEmpty(template)) {
            throw new RuleHandlingException("The template retrieved from the content file is a null or empty.");
        }

        logger.atInfo().log("取得した雛形 = (%s)", template);
        logger.atInfo().log("END");
        return template;
    }
}
