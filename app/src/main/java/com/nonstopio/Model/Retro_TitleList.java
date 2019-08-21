package com.nonstopio.Model;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Retro_TitleList
{
	@SerializedName("id")
	private String id;

	@SerializedName("title")
	private String title;

	@SerializedName("sub_paths")
	private List<Retro_SubPaths> sub_paths;

	public Retro_TitleList(String id, String title, List<Retro_SubPaths> sub_paths)
	{
		this.id = id;
		this.title = title;
		this.sub_paths = sub_paths;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public List<Retro_SubPaths> getSub_paths()
	{
		return sub_paths;
	}

	public void setSub_paths(List<Retro_SubPaths> sub_paths)
	{
		this.sub_paths = sub_paths;
	}
}
