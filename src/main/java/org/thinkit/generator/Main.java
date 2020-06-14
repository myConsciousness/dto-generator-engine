/**
 * Project Name : Generator<br>
 * File Name : Main.java<br>
 * Encoding : UTF-8<br>
 * Creation Date : 2020/05/16<br>
 * <p>
 * Copyright © 2020 Kato Shinya. All rights reserved.
 * <p>
 * This source code or any portion thereof must not be<br>
 * reproduced or used in any manner whatsoever.
 */

package org.thinkit.generator;

import com.google.common.flogger.FluentLogger;

import org.thinkit.common.catalog.Catalog;
import org.thinkit.generator.catalog.GeneratorDivision;

/**
 * Generatorプロジェクトのエントリーポイントクラスです。<br>
 * コマンドライン引数として渡された各データから業務に応じた生成器を判断して起動します。<br>
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 * 
 * @see #main()
 */
final class Main {

    /**
     * ログ出力オブジェクト
     */
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();

    /**
     * Generatorプロジェクトで使用する各生成器をコマンドライン引数から判定し起動します。<br>
     * <br>
     * 各生成器が処理を開始するために以下のコマンドライン引数が必要になります。<br>
     * 1. ファイルパス 各生成器が解析する対象の情報が記述されたファイルへのパスです。<br>
     * 2, 出力先パス 生成された情報を出力する領域のパスです。<br>
     * 3. 生成器区分 {@link GeneratorDivision} 起動する対象の生成器区分です。<br>
     * <br>
     * 各生成器の実行に必要なコマンドライン引数が渡されなかった場合は当メイン処理の実行時に必ず失敗します。
     *
     * @param args コマンドライン引数
     * @exception IllegalArgumentException 生成器の実行に必要な引数が渡されなかった場合
     */
    public static void main(String[] args) {
        logger.atInfo().log("START");

        if (args.length < 3) {
            logger.atSevere().log("Necessary to pass command line arguments in order to execute the process.");
            throw new IllegalArgumentException(String.format(
                    "wrong parameter was given. 3 parameter was expected but %s parameters were given.", args.length));
        }

        final String filePath = args[0];
        final String outputPath = args[1];
        final int generatorDivisionCode = Integer.parseInt(args[2]);

        if (!Catalog.hasCode(GeneratorDivision.class, generatorDivisionCode)) {
            logger.atSevere().log("An incorrect number was passed as a code value for GeneratorDivision.");
            throw new IllegalArgumentException(
                    "The code values passed as command line arguments are not defined in GeneratorDivision.");
        }

        final GeneratorDivision generatorDivision = Catalog.getEnum(GeneratorDivision.class, generatorDivisionCode);

        logger.atInfo().log("The file path passed as command line argument = (%s)", filePath);
        logger.atInfo().log("The generator division passed as command line argument = (%s)", generatorDivision);

        final Generator generator = GeneratorFactory.getInstance().create(generatorDivision, filePath, outputPath);

        if (!generator.execute()) {
            logger.atSevere().log("An unexpected error has occurred.");
            return;
        }

        logger.atInfo().log("END");
    }
}
