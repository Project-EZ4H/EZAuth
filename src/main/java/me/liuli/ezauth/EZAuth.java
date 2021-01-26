package me.liuli.ezauth;

import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class EZAuth extends PluginBase {
    public static EZAuth ezAuth;
    private static List<String> ips;

    @Override
    public void onLoad(){
        this.getServer().setPropertyBoolean("xbox-auth",false);
    }

    @Override
    public void onEnable() {
        ezAuth=this;
        File cfgFile=new File(this.getDataFolder(),"config.yml");
        if(!cfgFile.exists()){
            try {
                this.getDataFolder().mkdir();
                Writer writer=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cfgFile), StandardCharsets.UTF_8));
                writer.write("ips:\n" +
                        "- 127.0.0.1");
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Config config=new Config(cfgFile);
        ips=config.getStringList("ips");

        this.getServer().getPluginManager().registerEvents(new EventListener(), this);
    }

    public static boolean canBypass(String address){
        return ips.contains(address);
    }
}
