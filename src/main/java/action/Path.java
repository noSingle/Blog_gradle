package action;

import org.apache.struts2.ServletActionContext;

import java.io.File;

/**
 * Created by hjm0928 on 2017/4/29.
 */
public class Path {
    public String getClassPath(){
        String path = null;
        path = getClass().getResource("/").getPath();
        return path;
    }

    public String getServletPath(){
        String path;
        path = ServletActionContext.getServletContext().getRealPath("");
        return path;
    }

    public String getRequestPath(){
        return ServletActionContext.getRequest().getRequestURI();
    }

    public String IsFileExists(){
        File file = new File(getServletPath()+"\\"+"uploadFiles");
        return file.getAbsolutePath()+":"+file.exists();
    }
}
