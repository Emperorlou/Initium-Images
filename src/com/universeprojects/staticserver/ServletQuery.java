package com.universeprojects.staticserver;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.json.simple.JSONArray;

public class ServletQuery extends HttpServlet
{
	private static final long serialVersionUID = 8715060428325324298L;
	private static final String[] imageFileTypes = new String[]{".png", ".jpg", ".gif"};
	private static final String[] audioFileTypes = new String[]{".mp3", ".ogg", ".wav"};

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		String type = req.getParameter("type");
		
		if ("imageSearch".equals(type))
		{
			String filename = req.getParameter("term");
			String callback = req.getParameter("callback");
			
			List<String> images = findFiles(filename, "images", imageFileTypes);
			
			JSONArray json = new JSONArray();
			json.addAll(images);
			
			resp.setContentType("application/json");
			PrintWriter out = resp.getWriter();
			out.print(callback+"("+json.toJSONString()+")");
			out.flush();
			out.close();
		}
		else if ("audioSearch".equals(type))
		{
			String filename = req.getParameter("term");
			String callback = req.getParameter("callback");
			
			List<String> images = findFiles(filename, "audio", audioFileTypes);
			
			JSONArray json = new JSONArray();
			json.addAll(images);
			
			resp.setContentType("application/json");
			PrintWriter out = resp.getWriter();
			out.print(callback+"("+json.toJSONString()+")");
			out.flush();
			out.close();
		}
		else if ("musicSearch".equals(type))
		{
			String filename = req.getParameter("term");
			String callback = req.getParameter("callback");
			
			List<String> images = findFiles(filename, "music", audioFileTypes);
			
			JSONArray json = new JSONArray();
			json.addAll(images);
			
			resp.setContentType("application/json");
			PrintWriter out = resp.getWriter();
			out.print(callback+"("+json.toJSONString()+")");
			out.flush();
			out.close();
		}
	}
	
	
	List<String> findFiles(String filenameContains, String baseFolder, String[] fileTypes)
	{
		if (filenameContains!=null)
			filenameContains = filenameContains.toLowerCase();
		
		ArrayList<String> result = new ArrayList<String>();
		if (baseFolder!=null)
		{
			if (baseFolder.startsWith("/"))
				baseFolder = baseFolder.substring(1);
			File base = new File(baseFolder);
			Collection<File> files = FileUtils.listFiles(base, TrueFileFilter.INSTANCE, DirectoryFileFilter.DIRECTORY);
			for(File f:files)
			{
				
				String fname = f.getPath().toLowerCase();
				
				boolean goodExtension = false;
				for(String ext:fileTypes)
				{
					if (fname.endsWith(ext))
					{
						goodExtension = true;
						break;
					}
				}
				
				if (goodExtension==false) continue;

				
				boolean containsAll = true;
				if (filenameContains!=null)
				{
					String[] keywords = filenameContains.split(" ");
					containsAll = containsAll(fname, keywords);
				}
				
				if (filenameContains==null || containsAll)
				{
					fname = f.getPath();
					fname = fname.replace('\\', '/');
					result.add(fname);
				}
			}
		}
		return result;
	}
	
	private boolean containsAll(String source, String[] keywords)
	{
		if (source==null) return false;
		if (keywords==null || keywords.length==0) return true;
		
		for(String keyword:keywords)
			if (source.contains(keyword)==false)
				return false;
		
		return true;
	}

}
