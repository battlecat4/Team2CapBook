/*package com.cg.project.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Albums {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int albumId;
	private String albumName;
	
	@OneToMany(mappedBy="album")
	private List<Photos> photos;
	
	public Albums() {
		super();
	}
	public Albums(int albumId, String albumName) {
		super();
		this.albumId = albumId;
		this.albumName = albumName;
	}
	public int getAlbumId() {
		return albumId;
	}
	public void setAlbumId(int albumId) {
		this.albumId = albumId;
	}
	public String getAlbumName() {
		return albumName;
	}
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	
	public List<Photos> getPhotos() {
		return photos;
	}
	public void setPhotos(List<Photos> photos) {
		this.photos = photos;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + albumId;
		result = prime * result + ((albumName == null) ? 0 : albumName.hashCode());
		result = prime * result + ((photos == null) ? 0 : photos.hashCode());
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
		Albums other = (Albums) obj;
		if (albumId != other.albumId)
			return false;
		if (albumName == null) {
			if (other.albumName != null)
				return false;
		} else if (!albumName.equals(other.albumName))
			return false;
		if (photos == null) {
			if (other.photos != null)
				return false;
		} else if (!photos.equals(other.photos))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Albums [albumId=" + albumId + ", albumName=" + albumName + ", photos=" + photos + "]";
	}
	

}
*/