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

import com.google.common.flogger.FluentLogger;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
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
     * 生成する定義のパスを管理するオブジェクト
     */
    @Getter(AccessLevel.PRIVATE)
    private DefinitionPath definitionPath = null;

    /**
     * デフォルトコンストラクタ
     */
    @SuppressWarnings("unused")
    private AbstractGenerator() {
    }

    /**
     * コンストラクタ
     *
     * @param definitionPath 生成する定義のパスを管理するオブジェクト
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    protected AbstractGenerator(@NonNull DefinitionPath definitionPath) {
        this.definitionPath = definitionPath;
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

    /**
     * 定義書へのファイルパスを返却します。
     * 
     * @return 定義書へのファイルパス
     */
    protected String getFilePath() {
        return this.definitionPath.getFilePath();
    }

    /**
     * 出力先のパスを返却します。
     * 
     * @return 出力先へのパス
     */
    protected String getOutputPath() {
        return this.definitionPath.getOutputPath();
    }
}
