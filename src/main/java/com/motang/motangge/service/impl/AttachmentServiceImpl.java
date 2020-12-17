package com.motang.motangge.service.impl;

import com.motang.motangge.common.exception.BookException;
import com.motang.motangge.entity.Attachment;
import com.motang.motangge.mapper.AttachmentMapper;
import com.motang.motangge.service.IAttachmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description  附件表 服务实现类
 * @author liuhu
 * @Date 2020/12/16 14:03
 */
@Service
@Slf4j
public class AttachmentServiceImpl implements IAttachmentService {

    @Autowired
    private AttachmentMapper attachmentMapper;

    @Override
    public void saveAttachment(Attachment attachment) {
        try {
            attachmentMapper.insert(attachment);
        }catch (Exception e){
            log.error("保存附件信息失败！");
            throw new BookException("保存附件信息失败！");
        }
    }
}
