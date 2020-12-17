package com.motang.motangge.service;

import com.motang.motangge.entity.Attachment;

/**
 * @description 附件表 服务类
 * @author liuhu
 * @Date 2020/12/16 14:02
 */
public interface IAttachmentService{
    /**
     * @description 保存附件
     * @author liuhu
     * @Date 2020/12/16 14:11
     */
    void saveAttachment(Attachment attachment);
}
