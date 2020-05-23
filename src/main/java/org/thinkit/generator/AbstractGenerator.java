/**
 * Project Name : Generator<br>
 * File Name : AbstractGenerator.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/04/21<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator;

import org.apache.commons.lang3.StringUtils;

import com.google.common.flogger.FluentLogger;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * 定義書の解析処理における基底クラスです。
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode
public abstract class AbstractGenerator implements Generator {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * 操作する定義書までのファイルパス
     */
    @Getter(AccessLevel.PROTECTED)
    private String filePath = "";

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private AbstractGenerator() {
    }

    /**
     * コンストラクタ。
     *
     * @param filePath 操作する定義書までのファイルパス
     * @exception IllegalArumentException 引数として指定された文字列がnullまたは空文字列の場合
     */
    protected AbstractGenerator(final String filePath) {
        logger.atInfo().log("START");

        if (StringUtils.isEmpty(filePath)) {
            logger.atSevere().log("無効なファイルパスが渡されました。 ファイルパス = (%s)", filePath);
            logger.atSevere().log("初期化を行うためにはファイルパスが必須になります。");
            throw new IllegalArgumentException("wrong parameter was given. File path is required to initialize.");
        }

        this.filePath = filePath;

        logger.atInfo().log("END");
    }

    /**
     * メイン処理を定義する抽象メソッドです。
     *
     * @return 処理が正常終了した場合は{@code true}、それ以外は{@code false}
     */
    protected abstract boolean run();

    @Override
    public boolean execute() {
        logger.atInfo().log("START");

        try {
            if (!this.run()) {
                return false;
            }
        } catch (Exception e) {
            logger.atSevere().log("実行時に想定外のエラーが発生しました。");
            logger.atSevere().log("ログを解析し原因調査と修正を行ってください。");
            e.printStackTrace();
            return false;
        }

        logger.atInfo().log("END");
        return true;
    }
}
