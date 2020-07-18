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

import org.apache.commons.lang3.StringUtils;
import org.thinkit.common.catalog.Delimiter;
import org.thinkit.common.util.file.FluentFile;

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

    /**
     * パッケージ情報を付与した出力先のパスを返却します。<br>
     * パッケージ情報はカンマ区切りの文字列として渡してください。<br>
     * 例えば、以下のような形式で渡してください。<br>
     * 渡されたパッケージ情報はカンマの区切り文字をプラットフォームに応じたファイルセパレータに変換し出力先パスに付与します。<br>
     * <br>
     * {@code "org.thinkit.generator"}<br>
     * <br>
     * 引数として{@code null}が指定された場合は実行時に必ず失敗します。
     *
     * @param packageName カンマ区切りで表現されたパッケージ名
     * @return 出力先へのパス
     *
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    protected String getOutputPath(@NonNull String packageName) {

        if (packageName.length() <= 0) {
            return this.definitionPath.getOutputPath();
        }

        final StringBuilder outputPath = new StringBuilder();
        final String fileSeparator = FluentFile.getFileSeparator();

        outputPath.append(this.definitionPath.getOutputPath()).append(fileSeparator)
                .append(StringUtils.replace(packageName, Delimiter.period(), fileSeparator));

        return outputPath.toString();
    }
}
