package org.hy.common.report.junit.append.config;

import java.util.List;

import org.hy.common.Help;
import org.hy.common.app.Param;
import org.hy.common.xml.XJava;
import org.hy.common.xml.plugins.AppInitConfig;





/**
 * 初始化信息
 * 
 * @author      ZhengWei(HY)
 * @createDate  2014-09-12
 * @version     v1.0  
 */
public class InitConfig extends AppInitConfig
{
    private static boolean $Init = false;
    
    
    
    public InitConfig()
    {
        init();
    }
    
    
    
    @SuppressWarnings("unchecked")
    private synchronized void init()
    {
        if ( !$Init )
        {
            $Init = true;
            
            try
            {
                this.init("startup.Config.xml");
                this.init((List<Param>)XJava.getObject("StartupConfig") ,Help.getClassPath(this));
            }
            catch (Exception exce)
            {
                System.out.println(exce.getMessage());
                exce.printStackTrace();
            }
        }
    }
    
}
