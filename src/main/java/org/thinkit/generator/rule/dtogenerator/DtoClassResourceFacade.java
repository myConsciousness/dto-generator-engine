/**
 * Project Name : Generator<br>
 * File Name : DtoClassResourceFacade.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/06/13<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator.rule.dtogenerator;

import java.util.Map;

import com.google.common.flogger.FluentLogger;

import lombok.NonNull;

/**
 * DTOクラスのリソースを生成する処理を集約したファサードクラスです。<br>
 * DTOクラスのリソースを生成する際には{@link #createResource(String)}を呼び出してください。<br>
 * {@link #createResource(String)}を呼び出す際には、<br>
 * 第1引数としてDTOクラスの定義情報が記載されたExcelファイルのパスを指定してください。<br>
 * 
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * 
 * @see #createResource(String)
 */
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

    /**
     * 指定されたファイルパスに定義された情報を基にDTOクラスのリソースを生成します。<br>
     * 引数に{@code null}が指定された場合は実行時に必ず失敗します。<br>
     * <br>
     * 返却する連想配列は以下の情報を格納しています。<br>
     * 1, Key ・・・ クラス名<br>
     * 2, Value ・・・ クラス名に紐づくDTOクラスのリソース<br>
     * 
     * @param filePath
     * @return 生成されたDTOクラスのリソースを格納した連想配列
     * 
     * @exception NullPointerException 引数として{@code null}が渡された場合
     */
    public static Map<String, String> createResource(@NonNull String filePath) {
        logger.atInfo().log("START");

        final ClassDefinitionMatrixManager classDefinitionMatrixManager = new ClassDefinitionMatrixManager(filePath);

        if (!classDefinitionMatrixManager.execute()) {
            logger.atSevere().log("クラス定義情報群の生成処理が異常終了しました。");
            return null;
        }

        final ClassDefinitionMatrixFormatter classDefinitionMatrixFormatter = new ClassDefinitionMatrixFormatter(
                classDefinitionMatrixManager.getClassDefinitionMatrix());

        if (!classDefinitionMatrixFormatter.execute()) {
            logger.atSevere().log("クラス定義情報の整形処理が異常終了しました。");
            return null;
        }

        logger.atInfo().log("END");
        return classDefinitionMatrixFormatter.getFormattedResources();
    }
}