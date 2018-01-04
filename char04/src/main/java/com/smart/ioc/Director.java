package com.smart.ioc;

public class Director {
    public void direct() {
        //指定角色的饰演者
        GeLi geli = new LiuDeHua();

        //注入具体饰演者到剧本中
        MoAttack moAttack = new MoAttack();
        moAttack.injectGeli(geli);
        moAttack.cityGateAsk();
    }
}
