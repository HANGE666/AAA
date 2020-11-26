package AAA;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

public class AAA {
	
	//main
	public static void main(String[] args) {
		//fileNumber("E:\\BaiduNetdiskDownload\\2. SpringData\\05Spring Data-Spring Data Redis\\视频","05");
		//fileSuffix("H:\\尚硅谷前端学科全套教程\\5.前端学科--规范集成\\尚硅谷ES6教程\\视频");
		//deleteNullFile("E:\\BaiduNetdiskDownload\\3.框架\\11-SpringData\\2. SpringData");
		//getFileDirToTxt("E:\\BaiduNetdiskDownload\\3.框架\\11-SpringData\\11-SpringData");
		//fileNumber0("I:\\01-Hibernate\\新建文件夹", "0");
		//fileEditNameIndex("I:\\01-Hibernate",3);
		//fileEditNameSuffix("E:\\BaiduNetdiskDownload\\hadoop\\视频",'.');
		fileEditNameNull("E:\\07-SSM淘淘商城\\资料\\day02\\source\\e3-manager\\e3-manager-service","02-");
		//fileEditNameString("E:\\BaiduNetdiskDownload\\1.尚硅谷全套JAVA教程--基础阶段（73.36GB）\\尚硅谷图解Java设计模式\\视频",')',' ');
		//fileNumber("E:\\BaiduNetdiskDownload\\4.尚硅谷全套JAVA教程--JavaEE高级（44.51GB）\\JVM\\JVM上篇：内存与垃圾回收篇\\视频\\第1章：JVM与Java体系结构", "02");
		//getFirstChildDir("E:\\AA");
		
	}
	
	//根据时间顺序排序
	public static void fileCreateTimeOrder(String path) {
		List<File> list = getFiles(path, new ArrayList<File>());
		List<File> newList = getFileSort(path);
		Collections.reverse(newList);
		for (int i = 0; i < list.size(); i++) 
			for(int j = 0; j < newList.size();j++) {
				if (list.get(i).getName().equals(newList.get(j).getName())) {
					new File(list.get(i).getAbsolutePath())
					.renameTo(new File(String.valueOf(newList.get(j).getParent())
							+"\\"+String.valueOf(j+1)+"-"+newList.get(j).getName()));  
				}
			}
		System.out.println("时间排序成功");
	}
	
	
	//给文件名称最前添加指定数字，fileNumber("G:\\sss",1)
	public static void fileNumber(String path,int number) {
		List<File> list = getFiles(path, new ArrayList<File>());
		for(int i = 0;i<list.size();i++) {
			new File(list.get(i).getAbsolutePath())
			.renameTo(new File(String.valueOf(list.get(i).getParent())
					+"\\"+String.valueOf(number)+"-"+list.get(i).getName()));  
		}
	}
	
	public static void fileNumber(String path,String number) {
		List<File> list = getFiles(path, new ArrayList<File>());
		for(int i = 0;i<list.size();i++) {
			new File(list.get(i).getAbsolutePath())
			.renameTo(new File(String.valueOf(list.get(i).getParent())
					+"\\"+number+"-"+list.get(i).getName()));  
		}
		System.out.println(path+"成功添加了前缀"+number);
	}
	
	public static void fileNumber0(String path,String number) {
		List<File> list = getFiles(path, new ArrayList<File>());
		for(int i = 0;i<list.size();i++) {
			new File(list.get(i).getAbsolutePath())
			.renameTo(new File(String.valueOf(list.get(i).getParent())
					+"\\"+number+list.get(i).getName()));  
		}
		System.out.println(path+"下的所有文件名的前缀加了"+number+"成功");
	}
	
	//文件排序
	public static List<File> getFileSort(String path) {
		List<File> list = getFiles(path, new ArrayList<File>());
		if (list != null && list.size() > 0) {
			Collections.sort(list, new Comparator<File>() {
				public int compare(File file, File newFile) {
					if (file.lastModified() < newFile.lastModified()) {
						return 1;
					} else if (file.lastModified() == newFile.lastModified()) {
						return 0;
					} else {
						return -1;
					}
				}
			});
		}
		return list;
	}
	
	
	public static List<File> getFiles(String realpath, List<File> files) {
		File realFile = new File(realpath);
		if (realFile.isDirectory()) {
			File[] subfiles = realFile.listFiles();
			for (File file : subfiles) {
				if (file.isDirectory()) {
					getFiles(file.getAbsolutePath(), files);
				} else {
					files.add(file);
				}
			}
		}
		return files;
	}
	
	
	//移动指定文件夹下的文件下的指定的后缀名到指定文件夹下fileSuffix(String fromPath,String toPath,String suffix)
	public static void fileSuffix(String fromPath,String toPath,String suffix) {
		String changePath = toPath+"\\";
		File f = new File(fromPath);
		System.out.println(changePath);
		printFile(f, 0,changePath,suffix);
	}
	
	//提取指定文件夹的视频到指定文件夹新建的视频文件夹
	public static void fileSuffix(String fromPath) {
		File videoFile = new File(fromPath+"\\视频");
		videoFile.mkdirs();
		String changePath = videoFile.getAbsolutePath();
		changePath+="\\";
		System.out.println(changePath);
		File file = new File(fromPath);
		//获取文件后缀名，判断是否读入
		String string[] = {".wmv",".avi",".mp4",".flv",
							".mpeg",".swf",".qsv",".mkv",
							".mov",".rmvb",".3gb",".exe"};
		String suffix;
		for (int i = 0; i < string.length; i++) {
			suffix = string[i];
			printFile(file, 0, changePath, suffix);
		}
		
	}
	
	public static void printFile(File f, int i,String toPath,String suffix) {
		System.out.println("文件路径: " + f.getPath());
		if (f.isDirectory())
		{
			File[] files = f.listFiles();
			for (File temp : files) {
				printFile(temp, i + 1,toPath,suffix);
			}
		} else
		{
			try {
				File fileFrom = new File(f.getPath()); 
				String originalFilename = fileFrom.getAbsolutePath();
				String fileSuffix = originalFilename.substring(originalFilename.lastIndexOf("."));
				if (fileSuffix.equals(suffix)) {
					File fileTo = new File(toPath + fileFrom.getName()); 
					int count = 1;
					while (fileTo.exists()) {
						StringBuilder fileName = new StringBuilder(fileFrom.getName());
						fileName.insert(fileName.indexOf("."), "");
						//fileTo = new File(TOPATH + fileName);
						fileTo = new File(toPath + fileName);
						count++;
					}
					if (fileFrom.renameTo(fileTo)) 
						System.out.println("文件重命名成功");
					else
						System.out.println("文件重命名失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//删除指定文件夹所有空目录，deleteNullFile("e:\\test");
	public static void deleteNullFile(String dir_str) {
		int iFile = 0;
        File dir = new File( dir_str );
        clear(iFile,dir);
        System.out.println( "清除成功" );
	}
	public static void clear(int iFile,File dir)
    {
        File[] dirs = dir.listFiles();
        for( int i = 0; i < dirs.length; i++ )
        {
            if( dirs[i].isDirectory() )
            {
                clear(iFile,dirs[i]);
            }
        }
        if( dir.isDirectory() && dir.delete() )
            iFile++;
    }
	
	public static void fileEditNameSuffix(String path,char suffix) {
		getFileDirToTxt(path);
		List<File> list = getFiles(path, new ArrayList<File>());
		for(int i = 0;i<list.size();i++) {
			String oldName = new File(list.get(i).getName()).toString();
			String number = String.valueOf(i+1000)+".";
			String temp = oldName.substring(0, oldName.indexOf(suffix)+1);
			String newName = oldName.replaceAll(temp, number);	
			new File(list.get(i).getAbsolutePath())
			.renameTo(new File(String.valueOf(list.get(i).getParent())
					+"\\"+newName));
			
		}
		getFileDirToTxt(path);
	}
	
	public static void fileEditNameIndex(String path,int index) {
		getFileDirToTxt(path);
		List<File> list = getFiles(path, new ArrayList<File>());
		for(int i = 0;i<list.size();i++) {
			
			String oldName = new File(list.get(i).getName()).toString();
			String number = String.valueOf(i+1000)+".";
			String temp = oldName.substring(0, index);
			String newName = oldName.replaceAll(temp, number);
			new File(list.get(i).getAbsolutePath())
			.renameTo(new File(String.valueOf(list.get(i).getParent())
					+"\\"+newName));
		}
		getFileDirToTxt(path);
	}
	
	public static void fileEditNameNull(String path,String temp) {
		boolean flag = false;
		List<File> list = getFiles(path, new ArrayList<File>());
		for(int i = 0;i<list.size();i++) {
			String oldName = new File(list.get(i).getName()).toString();
			String newName = oldName.replaceAll(temp, "");
			new File(list.get(i).getAbsolutePath())
			.renameTo(new File(String.valueOf(list.get(i).getParent())
					+"\\"+newName));
			flag = true;
		}
		if (flag==true) {
			System.out.println(path+"清除了指定的字符串("+temp+")成功");
		}
	}
	
	public static void fileEditNameString(String path,char oldChar,char newChar) {
		boolean flag = false;
		List<File> list = getFiles(path, new ArrayList<File>());
		for(int i = 0;i<list.size();i++) {
			String oldName = new File(list.get(i).getName()).toString();
			String newName = oldName.replace(oldChar, newChar);
			new File(list.get(i).getAbsolutePath())
			.renameTo(new File(String.valueOf(list.get(i).getParent())
					+"\\"+newName));
			flag = true;
		}
		if (flag==true) {
			System.out.println(path+"清除了指定的字符串("+oldChar+")成功");
		}
	}
	
	public static void getAllFileName(String path,ArrayList<String> fileNameList) {
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
             System.out.println("文     件：" + tempList[i]);
                fileNameList.add(tempList[i].getName());
            }
            if (tempList[i].isDirectory()) {
                getAllFileName(tempList[i].getAbsolutePath(),fileNameList);
            }
        }
        return;
    }
	
	
	public static String getFileLength(String fileName) {
		File file = new File(fileName);
		String string = String.valueOf(file.length()/1024)+".kb";
		return string;
	}
	
	public static String getDuration(String fileName) {
		File file = new File(fileName);
        Encoder encoder = new Encoder();  
        long ls = 0;
        MultimediaInfo m;
        String string = null;
        try {
            m = encoder.getInfo(file);
            ls = m.getDuration()/1000;  
            System.out.println(ls);
            int hour = (int) (ls/3600);
            String hh = hour<10?(String.valueOf(0)+String.valueOf(hour)):String.valueOf(hour);
            int minter = (int)(ls/60);
            String mm = minter<10?(String.valueOf(0)+String.valueOf(minter)):String.valueOf(minter);
            int seconds = (int)(ls%60);
            String ss = seconds<10?(String.valueOf(0)+String.valueOf(seconds)):String.valueOf(seconds);
            string=hh+":"+mm+":"+ss;
            return string;
        } catch (Exception e) {
            System.out.println("获取音频时长有误：" + e.getMessage());
            return null;
        }
    }
	
	public static void CreateFile(String path) {
		String fileName = "目录.txt";
        File testFile = new File(path+"\\"+fileName);
        try {
			testFile.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void InsertStringToFile(String path,String fileName,String string) {
		String saveFile = path + "\\" + fileName;
		File file = new File(saveFile);
		try {
			file.createNewFile();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        try {
            fos = new FileOutputStream(file, true);
            osw = new OutputStreamWriter(fos, "utf-8");
            osw.write(string); //写入内容
            osw.write("\r\n");  //换行
        } catch (Exception e) {
            e.printStackTrace();
        }finally {   //关闭流
            try {
                if (osw != null) {
                    osw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
	}
	
	public static void getFileDirToTxt(String path) {
		boolean flag = false;
		List<File> list = getFiles(path, new ArrayList<File>());
		String dirName = "目录.txt";
        File dir = new File(path+"\\"+dirName);
        try {
        	dir.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for(int i = 0;i<list.size();i++) {
			String fileName = new File(list.get(i).getName()).toString();
			String absolutePath = new File(list.get(i).getAbsolutePath()).toString();
			String fileLength = getFileLength(absolutePath);
			String fileTime = getDuration(absolutePath);
			String index = null;
			if (i<10) {
				index = "000"+String.valueOf(i);
			}else if(10<=i&&i<=99) {
				index = "00" +String.valueOf(i);
			}else if (100<=i&&i<=990) {
				index = "0" +String.valueOf(i);
			}else {
				index = String.valueOf(i);
			}
			String string = "序号："+" "+index+"\t"+
    						"名称："+" "+fileName+"\t"+
							"大小："+" "+fileLength+"\t"+
							"时长："+" "+fileTime;
			InsertStringToFile(path,dirName,string);
			flag = true;
		}
		if (flag==true) {
			System.out.println("文件夹:"+path+""+"目录生成完毕");
		}
	}
	
	public static String getFileNameSuffix(String fileName) {
		String[] strArray = fileName.split("\\.");
		int suffixIndex =strArray.length-1;
		String string = strArray[suffixIndex];
		return string;
	}
	
	public static void lazyTime(int number) {
		new Thread() {
			public void run() {
				try {
					Thread.sleep(number);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
	}
	
	private static void getFirstChildDir(String path) {
		File file = new File(path);
		for(int i=0;i<file.length();i++) {
			
		}
	}
	
	private static void getOldFileName(String path,String fileName) {
		
	}
	
	public static String readFileContent(String fileName) {
	    File file = new File(fileName);
	    BufferedReader reader = null;
	    StringBuffer sbf = new StringBuffer();
	    try {
	        reader = new BufferedReader(new FileReader(file));
	        String tempStr;
	        while ((tempStr = reader.readLine()) != null) {
	            sbf.append(tempStr);
	        }
	        reader.close();
	        return sbf.toString();
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        if (reader != null) {
	            try {
	                reader.close();
	            } catch (IOException e1) {
	                e1.printStackTrace();
	            }
	        }
	    }
	    return sbf.toString();
	}
	
}

