package tech.kevinwong.common.exception.file;

import tech.kevinwong.common.exception.BaseException;

/**
 * 文件信息异常类
 * 
 * @author Kevin<EastascendWang@gmail.com>
 */
public class FileException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file", code, args, null);
    }

}
