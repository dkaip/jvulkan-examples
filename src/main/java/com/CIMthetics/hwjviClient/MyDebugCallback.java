package com.CIMthetics.hwjviClient;

import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.CIMthetics.jvulkan.VulkanCore.VK11.VkDebugReportCallback;
import com.CIMthetics.jvulkan.VulkanExtensions.VK11.Enums.VkDebugReportFlagBitsEXT;

public class MyDebugCallback implements VkDebugReportCallback
{
    private Logger log = LoggerFactory.getLogger("HWJVI_Client");;

    @Override
    public boolean invoke(EnumSet<VkDebugReportFlagBitsEXT> flags, int objectType,
            long object, long location, int messageCode, String layerPrefix,
            String messageText, Object userData)
    {
        
//        log.error("My Java debug callback LayerPrefix:{} MessageText:{}", layerPrefix, messageText);
        log.error("Debug Callback:{}", messageText);
        
//        log.error("Flags zzzz {}", flags.toString());
        
        // MUST return false;
        return false;
    }
}
