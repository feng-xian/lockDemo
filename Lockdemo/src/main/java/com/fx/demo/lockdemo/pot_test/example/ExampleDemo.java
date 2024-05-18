package com.fx.demo.lockdemo.pot_test.example;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.config.Configure;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.Rows;
import com.deepoove.poi.data.TableRenderData;
import com.deepoove.poi.data.Tables;
import com.deepoove.poi.data.style.BorderStyle;
import com.deepoove.poi.data.style.RowStyle;
import com.deepoove.poi.data.style.TableStyle;
import com.deepoove.poi.util.TableTools;
import org.apache.commons.compress.utils.Lists;
import org.apache.poi.xwpf.usermodel.TableRowAlign;
import org.apache.poi.xwpf.usermodel.XWPFTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExampleDemo {

    static String resource = "src/main/resources/static/tmp/payment.docx";

    static String outPath = "F:\\ISUPPicServer\\" + "s_example_" + System.currentTimeMillis() + ".docx";
    static PaymentData datas = new PaymentData();

    public static void main(String[] args) {
        runDemo();
    }

    public static void runDemo() {
        try {
            initData();
            Configure config = Configure.builder().bind("detail_table", new DetailTablePolicy()).build();
            XWPFTemplate template = XWPFTemplate.compile(resource, config).render(datas);

            template.writeToFile(outPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initData() {
        datas.setNO("KB.6890451");
        datas.setID("ZHANG_SAN_091");
        datas.setTaitou("深圳XX家装有限公司");
        datas.setConsignee("丙丁");

        datas.setSubtotal("8000");
        datas.setTax("600");
        datas.setTransform("120");
        datas.setOther("250");
        datas.setUnpay("6600");
        datas.setTotal("总共：7200");

        RowRenderData header = Rows.of("日期", "订单编号", "销售代表", "离岸价", "发货方式", "条款", "税号").bgColor("F2F2F2").center()
                .textColor("7F7f7F").textFontFamily("Hei").textFontSize(9).create();
        RowRenderData row = Rows.of("2018-06-12", "SN18090", "李四", "5000元", "快递", "附录A", "T11090").center().create();
        BorderStyle borderStyle = new BorderStyle();
        borderStyle.setColor("A6A6A6");
        borderStyle.setSize(4);
        borderStyle.setType(XWPFTable.XWPFBorderType.SINGLE);
        TableRenderData table = Tables.ofA4MediumWidth().addRow(header).addRow(row).border(borderStyle).center()
                .create();
        datas.setOrder(table);

        DetailData detailTable = new DetailData();
        RowRenderData good = Rows.of("4", "墙纸", "书房+卧室", "1500", "/", "400", "1600").center().create();

        RowStyle rowStyle = new RowStyle();
        rowStyle.setHeight(200);
        good.setRowStyle(rowStyle);

        List<RowRenderData> goods = Arrays.asList(good, good, good);
        RowRenderData labor = Rows.of("油漆工", "2", "200", "400").center().create();
        List<RowRenderData> labors = Arrays.asList(labor, labor, labor,labor, labor, labor, labor, labor,labor, labor, labor, labor, labor,labor, labor, labor);
        detailTable.setGoods(goods);
        detailTable.setLabors(buildGoods());
        datas.setDetailTable(detailTable);
    }

    public static List<RowRenderData> buildGoods(){
        ArrayList<RowRenderData> objects = Lists.newArrayList();
        for (int i = 0; i < 26; i++) {
            RowRenderData labor = Rows.of("油漆工" + (i + 1), String.valueOf(i), "200", "400").center().create();
            objects.add(labor);
        }
        return objects;
    }

}
