package count;

import util.keyVal;

/**
 * Created by lxorc on 7/30/2017.
 */
public class findInMon {

    public static void findToSav() {
        //29999 不区分wa 和 ma
        getMonDat.mongoFind(keyVal.DSta, keyVal.mongo29999);
        getMonDat.getResult("总用户制作数\t", keyVal.DSta, keyVal.AlbPer);

        //3000miniAPP 日统计
        getMonDat.mongoFind(keyVal.MaUpd, keyVal.mongo30001);
        getMonDat.getResult("ma用户首次上传照片\t", keyVal.MaUpd, keyVal.Uview);

        getMonDat. mongoFind(keyVal.MaCom, keyVal.mongo30001);
        getMonDat.getResult("ma用户首次提交影集\t", keyVal.MaCom, keyVal.Uview);

        getMonDat.mongoFind(keyVal.MaReadSharAl, keyVal.mongo30001);
        getMonDat.getResult("ma读者分享\t", keyVal.MaReadSharAl, keyVal.Uview, keyVal.Pview);

        getMonDat.mongoFind(keyVal.MaAuthSharAl, keyVal.mongo30001);
        getMonDat.getResult("ma作者分享\t", keyVal.MaAuthSharAl, keyVal.Uview, keyVal.Pview);

        getMonDat.mongoFind(keyVal.MaPerD, keyVal.mongo30001);
        getMonDat.getResult("ma总UV\t", keyVal.MaPerD, keyVal.MapgEnSuc);

        getMonDat.mongoFind(keyVal.MaDfqsAuthAl, keyVal.mongo30001);
        getMonDat.getResult("ma作者通过二维码社交唤醒回流\t", keyVal.MaDfqsAuthAl, keyVal.Uview, keyVal.Pview);
        getMonDat.mongoFind(keyVal.MaDfsAuthAl, keyVal.mongo30001);
        getMonDat.getResult("ma作者通过链接社交唤醒回流\t", keyVal.MaDfsAuthAl, keyVal.Uview, keyVal.Pview);

        getMonDat.mongoFind(keyVal.MaDfqsReadAl, keyVal.mongo30001);
        getMonDat.getResult("ma读者通过二维码社交唤醒回流\t", keyVal.MaDfqsReadAl, keyVal.Uview, keyVal.Pview);
        getMonDat.mongoFind(keyVal.MaDfsReadAl, keyVal.mongo30001);
        getMonDat.getResult("ma读者通过链接社交唤醒回流\t", keyVal.MaDfsReadAl, keyVal.Uview, keyVal.Pview);

//		3000webAPP 日统计
        getMonDat.mongoFind(keyVal.OutAuthSharAl, keyVal.mongo30001);
        getMonDat.getResult("wa作者分享\t", keyVal.OutAuthSharAl, keyVal.Uview, keyVal.Pview);

        getMonDat.mongoFind(keyVal.OutAuthSharBacAl, keyVal.mongo30001);
        getMonDat.getResult("wa作者社交唤醒回流\t", keyVal.OutAuthSharBacAl, keyVal.Uview, keyVal.Pview);

        getMonDat.mongoFind(keyVal.OutReadSharAl, keyVal.mongo30001);
        getMonDat.getResult("wa读者分享\t", keyVal.OutReadSharAl, keyVal.Uview, keyVal.Pview);

        getMonDat.mongoFind(keyVal.OutReadSharBacAl, keyVal.mongo30001);
        getMonDat.getResult("wa读者社交唤醒回流\n", keyVal.OutReadSharBacAl, keyVal.Uview, keyVal.Pview);

//		//wa用户的第一次
        getMonDat.mongoFind(keyVal.InUpd, keyVal.mongo30001);
        getMonDat.getResult("wa用户首次上传照片\t", keyVal.InUpd, keyVal.Uview);

        getMonDat.mongoFind(keyVal.InCom, keyVal.mongo30001);
        getMonDat.getResult("wa用户首次提交影集\t", keyVal.InCom, keyVal.Uview);
    }

    public static void findToSumSav(){
        //ma作者回流 sum
        /**
         * 流程* find（获取json串）* get（获取需要的kv） * add--》str[]
         * */
        getMonDat.getResultSum("ma作者总回流",keyVal.MaDfqsAuthAl,keyVal.MaDfsAuthAl, keyVal.Uview, keyVal.Pview);
        getMonDat.getResultSum("ma读者总回流",keyVal.MaDfqsReadAl,keyVal.MaDfsReadAl, keyVal.Uview, keyVal.Pview);
    }
    public static void findLocal() {
        //本地测试
        getMonDat.mongoFind(keyVal.MaUpd, keyVal.test);
        getMonDat.getResult("wa读者社22唤醒回流\t", keyVal.MaUpd, keyVal.Uview, keyVal.Pview);
    }

}
