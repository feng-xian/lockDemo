package com.fx.demo.lockdemo.pot_test.example;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.policy.DynamicTableRenderPolicy;
import com.deepoove.poi.policy.TableRenderPolicy;
import com.deepoove.poi.util.TableTools;

/**
 * 付款通知书 明细表格的自定义渲染策略<br/>
 * 1. 填充货品数据 <br/>
 * 2. 填充人工费数据 <br/>
 *
 * @author Sayi
 */
public class DetailTablePolicy extends DynamicTableRenderPolicy {

    // 货品填充数据所在行数
    int goodsStartRow = 2;
    // 人工费填充数据所在行数
    int laborsStartRow = 5;

    @Override
    public void render(XWPFTable table, Object data) throws Exception {
        if (null == data) return;
        DetailData detailData = (DetailData) data;

        List<RowRenderData> labors = detailData.getLabors();
        if (null != labors) {
            table.removeRow(laborsStartRow);
            // 循环插入行
            for (int i = 0; i <= labors.size(); i++) {
                XWPFTableRow insertNewTableRow = table.insertNewTableRow(laborsStartRow);
                for (int j = 0; j < 7; j++) {
                    insertNewTableRow.createCell();
                }

                if (i > 0) {
                    // 合并单元格
                    TableTools.mergeCellsHorizonal(table, laborsStartRow, 0, 3);
                    TableRenderPolicy.Helper.renderRow(table.getRow(laborsStartRow), labors.get(i - 1));
                } else {
                    TableTools.mergeCellsHorizonal(table, laborsStartRow, 0, 2);
                    TableTools.mergeCellsHorizonal(table, laborsStartRow, 1, 6);

                }

            }
            merg(labors.size(), table);
            addPic2(table, labors.size());
        }

        List<RowRenderData> goods = detailData.getGoods();
        if (null != goods) {
            table.removeRow(goodsStartRow);
            for (int i = 0; i < goods.size(); i++) {
                XWPFTableRow insertNewTableRow = table.insertNewTableRow(goodsStartRow);
                for (int j = 0; j < 7; j++) {
                    insertNewTableRow.createCell();
                }

                if (i == 0) {
                    // 设置行高
                    insertNewTableRow.setHeight(500);
                    insertNewTableRow.setHeightRule(TableRowHeightRule.AT_LEAST);
                }
                TableRenderPolicy.Helper.renderRow(table.getRow(goodsStartRow), goods.get(i));
            }


        }

    }

    public void merg(int size, XWPFTable table) {

        for (int i = 0; i < size; i++) {

            if (i % 2 == 0) {
                TableTools.mergeCellsVertically(table, 0, laborsStartRow + i, laborsStartRow + i + 1);
            }

        }

    }

    public void addPic2(XWPFTable table, int size) throws Exception {

        XWPFParagraph p1 = table.getRow(laborsStartRow + size).getCell(0).addParagraph();
        XWPFParagraph p2 = table.getRow(laborsStartRow + size).getCell(1).addParagraph();

        XWPFRun run = p1.createRun();

        p1.setAlignment(ParagraphAlignment.CENTER);
        p2.setAlignment(ParagraphAlignment.CENTER);

        InputStream stream = new FileInputStream("F:\\ISUPPicServer\\111_pp.jpg");
        InputStream stream2 = new FileInputStream("F:\\ISUPPicServer\\111_ff.jpg");
        run.addPicture(stream, XWPFDocument.PICTURE_TYPE_JPEG, "Generated"
                , Units.toEMU(200)
                , Units.toEMU(220));

        XWPFRun p2Run = p2.createRun();


        p2Run.addPicture(stream2, XWPFDocument.PICTURE_TYPE_JPEG, "Generated"
                , Units.toEMU(200)
                , Units.toEMU(220));
    }


}
