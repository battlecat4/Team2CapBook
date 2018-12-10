package com.cg.project.beans;
import java.util.Arrays;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Photos {
	@Id
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid",strategy="uuid")
	private String photoId;
	private String photoName;
	private String photoType;
	@Lob
	private byte[] data;
	
	/*@ManyToOne
	private Albums album;*/
	public Photos() {
		super();
	}
	public Photos(String photoId, String photoName, String photoType, byte[] data) {
		super();
		this.photoId = photoId;
		this.photoName = photoName;
		this.photoType = photoType;
		this.data = data;
	}
	
	public Photos(String photoName, String photoType, byte[] data) {
		super();
		this.photoName = photoName;
		this.photoType = photoType;
		this.data = data;
	}
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getPhotoType() {
		return photoType;
	}
	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(data);
		result = prime * result + ((photoId == null) ? 0 : photoId.hashCode());
		result = prime * result + ((photoName == null) ? 0 : photoName.hashCode());
		result = prime * result + ((photoType == null) ? 0 : photoType.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Photos other = (Photos) obj;
		if (!Arrays.equals(data, other.data))
			return false;
		if (photoId == null) {
			if (other.photoId != null)
				return false;
		} else if (!photoId.equals(other.photoId))
			return false;
		if (photoName == null) {
			if (other.photoName != null)
				return false;
		} else if (!photoName.equals(other.photoName))
			return false;
		if (photoType == null) {
			if (other.photoType != null)
				return false;
		} else if (!photoType.equals(other.photoType))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Photos [photoId=" + photoId + ", photoName=" + photoName + ", photoType=" + photoType + ", data="
				+ Arrays.toString(data) + "]";
	}
	
	/*public Albums getAlbum() {
		return album;
	}
	public void setAlbum(Albums album) {
		this.album = album;
	}*/
	
	

}
