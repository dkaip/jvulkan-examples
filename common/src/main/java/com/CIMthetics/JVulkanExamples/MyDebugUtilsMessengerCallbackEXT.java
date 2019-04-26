package com.CIMthetics.JVulkanExamples;

import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.CIMthetics.jvulkan.VulkanCore.VK11.VkDebugUtilsMessengerCallbackEXT;
import com.CIMthetics.jvulkan.VulkanExtensions.VK11.Enums.VkDebugUtilsMessageSeverityFlagBitsEXT;
import com.CIMthetics.jvulkan.VulkanExtensions.VK11.Enums.VkDebugUtilsMessageTypeFlagBitsEXT;
import com.CIMthetics.jvulkan.VulkanExtensions.VK11.Structures.VkDebugUtilsMessengerCallbackDataEXT;

public class MyDebugUtilsMessengerCallbackEXT implements VkDebugUtilsMessengerCallbackEXT
{
    private Logger log = LoggerFactory.getLogger("jvulkan-example");;

    @Override
    public boolean invoke(VkDebugUtilsMessageSeverityFlagBitsEXT messageSeverity,
                          EnumSet<VkDebugUtilsMessageTypeFlagBitsEXT> messageType,
                          VkDebugUtilsMessengerCallbackDataEXT callbackData,
                          Object userData)
    {
        log.debug("DebugMessengerUtilsCallback");
        return false;
    }

}
