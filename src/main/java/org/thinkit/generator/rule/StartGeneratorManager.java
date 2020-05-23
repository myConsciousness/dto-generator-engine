/**
 * Project Name : Business Tool<br>
 * File Name : StartGeneratorManager.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/16<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */
package org.thinkit.generator.rule;

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
import org.thinkit.generator.catalog.GeneratorDivision;

import com.google.common.flogger.FluentLogger;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 起動生成器の判定処理を行うルールです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class StartGeneratorManager extends AbstractRule {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * 生成器区分
     */
    @Getter(AccessLevel.PRIVATE)
    private GeneratorDivision generatorCategory = null;

    /***
     * パッケージ名
     */
    @Getter
    private String packageName = "";


    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private StartGeneratorManager() {}

    /**
     * コンストラクタ
     *
     * @param generatorCategory 生成器区分
     */
    public StartGeneratorManager(GeneratorDivision generatorCategory) {
        logger.atInfo().log("生成器区分 = (%s)", generatorCategory);
        Objects.requireNonNull(generatorCategory, "wrong parameter was given. Generator category is required.");

        this.generatorCategory = generatorCategory;

        super.loadContent(ContentName.起動生成器情報);
    }

    /**
     * コンテンツ名定数
     */
    private enum ContentName implements Content {
        起動生成器情報;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * コンテンツ要素定数
     */
    private enum ContentAttribute implements Attribute {
        パッケージ名;

        @Override
        public String getString() {
            return this.name();
        }
    }

    /**
     * コンテンツ条件定数
     */
    private enum ContentCondition implements Condition {
        生成器区分;

        @Override
        public String getString() {
            return this.name();
        }
    }

    @Override
    public boolean execute() {
        logger.atInfo().log("START");

        final List<Map<String, String>> contents = super.getContents();

        final Map<String, String> content = contents.get(0);
        final String packageName = content.get(ContentAttribute.パッケージ名.name());

        if (StringUtils.isEmpty(packageName)) {
            logger.atSevere().log("パッケージ名をコンテンツから取得できませんでした。");
            return false;
        }

        this.packageName = packageName;

        logger.atInfo().log("起動パッケージ名 = (%s)", packageName);
        logger.atInfo().log("END");
        return true;
    }

    @Override
    protected List<Attribute> getAttributes() {
        logger.atInfo().log("START");

        final List<Attribute> attributes = new ArrayList<>(1);
        attributes.add(ContentAttribute.パッケージ名);

        logger.atInfo().log("起動生成器情報のアトリビュート = (%s)", attributes);
        logger.atInfo().log("END");
        return attributes;
    }

    @Override
    protected Map<Condition, String> getConditions() {
        logger.atInfo().log("START");

        final Map<Condition, String> conditions = new HashMap<>(1);
        conditions.put(ContentCondition.生成器区分, String.valueOf(this.getGeneratorCategory().getCode()));

        logger.atInfo().log("起動生成器情報の条件 = (%s)", conditions);
        logger.atInfo().log("END");
        return conditions;
    }
}
