package com.CIMthetics.JVulkanExamples;

import java.util.EnumSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.CIMthetics.jvulkan.VulkanCore.VkDebugUtilsMessengerCallbackEXT;
import com.CIMthetics.jvulkan.VulkanExtensions.Enums.VkDebugUtilsMessageSeverityFlagBitsEXT;
import com.CIMthetics.jvulkan.VulkanExtensions.Enums.VkDebugUtilsMessageTypeFlagBitsEXT;
import com.CIMthetics.jvulkan.VulkanExtensions.Structures.VkDebugUtilsMessengerCallbackDataEXT;

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
        log.debug("    Message Severity:{}", messageSeverity.toString());
        log.debug("    MessageType:{}", messageType.toString());
        log.debug("    CallbackData.messageIdName:{}", callbackData.getMessageIdName());
        log.debug("    CallbackData.messageIdNumber:{}", callbackData.getMessageIdNumber());
        log.debug("    CallbackData.message:{}", callbackData.getMessage());
        log.debug("    CallbackData.queueLabels:{}", callbackData.getQueueLabels().toString());
        log.debug("    CallbackData.cmdBufLabels:{}", callbackData.getCmdBufLabels().toString());
        log.debug("    CallbackData.objects:{}", callbackData.getObjects().toString());
        log.debug("    UserData:{}", userData == null ? "null" : "not null");
        
        return false;
    }

}
