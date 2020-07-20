package com.CIMthetics.JVulkanExamples;

import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.CIMthetics.jvulkan.VulkanCore.VkDebugReportCallback;
import com.CIMthetics.jvulkan.VulkanExtensions.Enums.VkDebugReportFlagBitsEXT;

public class MyDebugCallback implements VkDebugReportCallback
{
    private Logger log = LoggerFactory.getLogger("jvulkan-example");;

    @Override
    public boolean invoke(EnumSet<VkDebugReportFlagBitsEXT> flags,
                          int objectType,
                          long object,
                          long location,
                          int messageCode,
                          String layerPrefix,
                          String messageText,
                          Object userData)
    {
//        log.error("My Java debug callback LayerPrefix:{} MessageText:{}", layerPrefix, messageText);
        log.error("Debug Callback:{}", messageText);

        // MUST return false;
        return false;
    }
}
