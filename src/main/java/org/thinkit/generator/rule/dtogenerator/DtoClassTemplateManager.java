/**
 * Project Name : Business Tool<br>
 * File Name : DtoClassTemplateManager.java<br>
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.thinkit.common.rule.AbstractRule;
import org.thinkit.common.rule.Attribute;
import org.thinkit.common.rule.Condition;
import org.thinkit.common.rule.Content;
import org.thinkit.generator.catalog.dtogenerator.DtoClassTemplate;

import com.google.common.flogger.FluentLogger;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * コンテンツからDTOクラス雛形情報を取得する処理を行うルールです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class DtoClassTemplateManager extends AbstractRule {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * DTO雛形区分
     */
    @Getter(AccessLevel.PRIVATE)
    private DtoClassTemplate dtoTemplateCategory = null;

    /**
     * DTOクラスの雛形
     */
    @Getter
    private String dtoClassTemplate = "";

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private DtoClassTemplateManager() {}

    /**
     * コンストラクタ
     *
     * @param dtoTemplateCategory DTO雛形区分
     */
    public DtoClassTemplateManager(DtoClassTemplate dtoTemplateCategory) {
        logger.atInfo().log("DTO雛形区分 = (%s)", dtoTemplateCategory);
        Objects.requireNonNull(dtoTemplateCategory, "wrong parameter was given. Object is null.");

        this.dtoTemplateCategory = dtoTemplateCategory;

        super.loadContent(ContentName.DTOクラス雛形情報);
    }

    /**
     * コンテンツ名定数
     */
    private enum ContentName implements Content {
        DTOクラス雛形情報;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * コンテンツ要素定数
     */
    private enum ContentAttribute implements Attribute {
        雛形コード, 雛形;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * コンテンツ条件定数
     */
    private enum ContentCondition implements Condition {
        DTO雛形区分;

        @Override
        public String getString() {
            return this.name();
        }
    }

    @Override
    public boolean execute() {
        logger.atInfo().log("START");

        final List<Map<String, String>> contents = super.getContents();
        final String dtoTemplate = contents.get(0).get(ContentAttribute.雛形.name());

        if (StringUtils.isEmpty(dtoTemplate)) {
            logger.atSevere().log("DTOクラス雛形情報をコンテンツから取得できませんでした。");
            return false;
        }

        this.dtoClassTemplate = dtoTemplate;

        logger.atInfo().log("取得したDTOクラス雛形情報 = (%s)", dtoTemplate);
        logger.atInfo().log("END");
        return true;
    }

    @Override
    protected List<Attribute> getAttributes() {
        logger.atInfo().log("START");

        final List<Attribute> attributes = new ArrayList<>(2);
        attributes.add(ContentAttribute.雛形コード);
        attributes.add(ContentAttribute.雛形);

        logger.atInfo().log("DTOクラス雛形情報のアトリビュート = (%s)", attributes);
        logger.atInfo().log("END");
        return attributes;
    }

    @Override
    protected Map<Condition, String> getConditions() {
        logger.atInfo().log("START");

        final Map<Condition, String> conditions = new HashMap<>(1);
        conditions.put(ContentCondition.DTO雛形区分, String.valueOf(this.getDtoTemplateCategory().getCode()));

        logger.atInfo().log("DTOクラス雛形情報の条件 = (%s)", conditions);
        logger.atInfo().log("END");
        return conditions;
    }
}
