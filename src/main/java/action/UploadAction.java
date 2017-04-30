package action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * Created by hjm0928 on 2017/4/28.
 */
public class UploadAction extends ActionSupport {
    private File upload;
    private String uploadContextType;
    private String uploadFileName;
    private String username = "pigge";

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContextType() {
        return uploadContextType;
    }

    public void setUploadContextType(String uploadContextType) {
        this.uploadContextType = uploadContextType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    @Override
    public String execute() throws Exception {
        FileInputStream fis = new FileInputStream(getUpload());
        File savePath = new File(ServletActionContext.getServletContext()
                .getRealPath("")+"\\uploadFiles\\"+username+"\\images");
        if(!savePath.exists())
            savePath.mkdirs();
        FileOutputStream fos = new FileOutputStream(savePath.getAbsolutePath()+"\\"+getUploadFileName());
        byte buffer[] = new byte[1024];
        int len = 0;
        while((len=fis.read(buffer))>0){
            fos.write(buffer,0, len);
        }
        PrintWriter out = ServletActionContext.getResponse().getWriter();
        String callback =ServletActionContext.getRequest().getParameter("CKEditorFuncNum");
        out.println("<script type=\"text/javascript\">");
        out.println("window.parent.CKEDITOR.tools.callFunction("+ callback + ",'/" +"uploadFiles/"+username+"/images/"+getUploadFileName() + "','')");
        out.println("</script>");
        fos.flush();
        fos.close();
        out.flush();
        out.close();
        return null;
    }
}
