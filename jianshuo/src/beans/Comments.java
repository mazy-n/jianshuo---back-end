package beans;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Comments {
	private int id;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	private String name;
	private String content;
	private String data;
	public String getName() {
		return name;
	}
	public void setName(String nickname) {
		this.name = nickname;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getData() {
		return data;
	}
	public void setData(Timestamp timestamp) {
		Date dat = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat yf = new SimpleDateFormat("yyyy");
		SimpleDateFormat mf = new SimpleDateFormat("MM-dd");
		SimpleDateFormat mdhm = new SimpleDateFormat("MM-dd HH:mm");
		SimpleDateFormat hm = new SimpleDateFormat("HH:mm");
		if(yf.format(dat.getTime()).equals(yf.format(timestamp))){
			if(mf.format(dat.getTime()).equals(mf.format(timestamp))){
				this.data = "½ñÌì "+hm.format(timestamp);
			}else{
				this.data = mdhm.format(timestamp);
			}
		}else{
			this.data = df.format(timestamp);
		}
	}
}
