package me.macjuul.chfiles;

import com.laytonsmith.PureUtilities.Common.FileUtil;
import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.Security;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.*;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;
import com.laytonsmith.libs.org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileFunctions {
	public static String docs() {
		return "Adds file interaction to MethodScript";
	}
	
	@api
	public static class copy_file extends AbstractFunction {
		@SuppressWarnings("unchecked")
		@Override
		public Class<? extends CREThrowable>[] thrown() {
	        return new Class[]{
	                CREFormatException.class,
	                CREIOException.class,
	                CRESecurityException.class
	        };
	    }

		@Override
	    public boolean isRestricted() {
	        return true;
	    }

	    @Override
	    public Boolean runAsync() {
	        return true;
	    }

	    @Override
	    public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {
	        File fromLoc = new File(t.file().getParentFile(), args[0].val());
	        File toLoc = new File(t.file().getParentFile(), args[1].val());
	        if(!Security.CheckSecurity(fromLoc) || !Security.CheckSecurity(toLoc)) {
	            throw new CRESecurityException("You do not have access to some of the files", t);
	        }
	        try {
	            if(fromLoc.isDirectory()) {
	                FileUtils.copyDirectory(fromLoc, toLoc);
	            } else if(fromLoc.isFile()) {
	                FileUtils.copyFile(fromLoc, toLoc);
	            }
	            return CVoid.VOID;
	        } catch (IOException e) {
	            throw new CREIOException("File could not be written in.", t);
	        }
	    }

	    @Override
	    public String getName() {
	        return "copy_file";
	    }

	    @Override
	    public Integer[] numArgs() {
	        return new Integer[]{2};
	    }

	    @Override
	    public String docs() {
	        return "{file | dir, dir} void Copy file or directory to another directory.";
	    }

	    @Override
	    public Version since() {
	        return new SimpleVersion(1, 0, 0);
	    }
	}
	
	@api
	public static class create_file extends AbstractFunction {
	    @SuppressWarnings("unchecked")
	    @Override
		public Class<? extends CREThrowable>[] thrown() {
	        return new Class[]{
	                CREIOException.class,
	                CRESecurityException.class
	        };
	    }

	    @Override
	    public boolean isRestricted() {
	        return true;
	    }

	    @Override
	    public Boolean runAsync() {
	        return true;
	    }

	    @Override
	    public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {
	        File loc = new File(t.file().getParentFile(), args[0].val());
	        if(!Security.CheckSecurity(loc)) {
	            throw new CRESecurityException("You do not have permission to access the file '" + loc.getAbsolutePath() + "'", t);
	        }
	        try {
	            if(loc.exists()) {
	                throw new CREIOException(loc.getAbsolutePath() + "Already Exists", t);
	            }
	            if(args.length == 2) {
	                FileUtil.write(args[1].val(), loc, true);
	            } else {
	                FileUtil.write("", loc, true);
	            }
	            return CVoid.VOID;
	        } catch (IOException e) {
	            throw new CREIOException("File could not be created.", t);
	        }
	    }

	    @Override
	    public String getName() {
	        return "create_file";
	    }

	    @Override
	    public Integer[] numArgs() {
	        return new Integer[]{1};
	    }

	    @Override
	    public String docs() {
	        return "{file} void creates a file.";
	    }

	    @Override
	    public Version since() {
	        return new SimpleVersion(1, 0, 0);
	    }
	}
	
	@api
	public static class delete_file extends AbstractFunction {
	    @SuppressWarnings("unchecked")
	    @Override
		public Class<? extends CREThrowable>[] thrown() {
	        return new Class[]{
	                CREIOException.class,
	                CRESecurityException.class
	        };
	    }

	    @Override
	    public boolean isRestricted() {
	        return true;
	    }

	    @Override
	    public Boolean runAsync() {
	        return true;
	    }

	    @Override
	    public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {
	        File loc = new File(t.file().getParentFile(), args[0].val());
	        if(!Security.CheckSecurity(loc)) {
	            throw new CRESecurityException("You do not have permission to access the file '" + loc.getAbsolutePath() + "'", t);
	        }
	        try {
	            if(!loc.exists()) {
	                throw new CREIOException(loc.getAbsolutePath() + "Doesn't exists", t);
	            }
	            if(loc.isDirectory()) {
	                FileUtils.deleteDirectory(loc);
	            } else if(loc.isFile()) {
	                FileUtils.forceDelete(loc);
	            }
	            return CVoid.VOID;
	        } catch (IOException e) {
	            throw new CREIOException("File could not be deleted.", t);
	        }
	    }

	    @Override
	    public String getName() {
	        return "delete_file";
	    }

	    @Override
	    public Integer[] numArgs() {
	        return new Integer[]{1};
	    }

	    @Override
	    public String docs() {
	        return "{file} void delete a file.";
	    }

	    @Override
	    public Version since() {
	        return new SimpleVersion(1, 0, 0);
	    }
	}
	
	@api
	public static class file_exists extends AbstractFunction {
	    @SuppressWarnings("unchecked")
	    @Override
	    public Class<? extends CREThrowable>[] thrown() {
	        return new Class[]{
	                CRESecurityException.class
	        };
	    }

	    @Override
	    public boolean isRestricted() {
	        return true;
	    }

	    @Override
	    public Boolean runAsync() {
	        return true;
	    }

	    @Override
	    public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {
	        File loc = new File(t.file().getParentFile(), args[0].val());
	        if(!Security.CheckSecurity(loc)) {
	            throw new CRESecurityException("You do not have permission to access the file '" + loc.getAbsolutePath() + "'", t);
	        }
	        return CBoolean.get(loc.exists());
	    }

	    @Override
	    public String getName() {
	        return "file_exists";
	    }

	    @Override
	    public Integer[] numArgs() {
	        return new Integer[]{1};
	    }

	    @Override
	    public String docs() {
	        return "{file} boolean if file exists, return true.";
	    }

	    @Override
	    public Version since() {
	        return new SimpleVersion(1, 0, 0);
	    }
	}
	
	@api
	public static class list_files extends AbstractFunction {

	    @SuppressWarnings("unchecked")
	    @Override
	    public Class<? extends CREThrowable>[] thrown() {
	        return new Class[] {
                CRESecurityException.class,
                CREIOException.class
	        };
	    }

	    @Override
	    public boolean isRestricted() {
	        return true;
	    }

	    @Override
	    public Boolean runAsync() {
	        return true;
	    }

	    @Override
	    public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {
	        File loc = new File(t.file().getParentFile(), args[0].val());

	        if(!Security.CheckSecurity(loc)) {
	            throw new CRESecurityException("You do not have permission to access the file '" + loc.getAbsolutePath() + "'", t);
	        }

	        CArray ret = new CArray(t);

	        if(loc.exists() && loc.isDirectory()) {
	            String[] list = loc.list();

	            if(list == null)
	            	throw new CRENotFoundException("Unable to locate directory", t);

	            for(String file : list) {
	                ret.push(new CString(file, t), t);
	            }
	        } else {
	            throw new CREIOException("The specified path is not a directory", t);
	        }
	        return ret;
	    }

	    @Override
	    public String getName() {
	        return "list_files";
	    }

	    @Override
	    public Integer[] numArgs() {
	        return new Integer[]{1};
	    }

	    @Override
	    public String docs() {
	        return "{directory} array return files in directory.";
	    }

	    @Override
	    public Version since() {
	        return new SimpleVersion(1, 0, 0);
	    }
	}
	
	@api
	public static class rename_file extends AbstractFunction {
	    @SuppressWarnings("unchecked")
	    @Override
	    public Class<? extends CREThrowable>[] thrown() {
	        return new Class[]{
	                CREIOException.class,
	                CRESecurityException.class
	        };
	    }

	    @Override
	    public boolean isRestricted() {
	        return true;
	    }

	    @Override
	    public Boolean runAsync() {
	        return true;
	    }

	    @Override
	    public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {
	        File loc = new File(t.file().getParentFile(), args[0].val());
	        if(!Security.CheckSecurity(loc)) {
	            throw new CRESecurityException("You do not have permission to access the file '" + loc.getAbsolutePath() + "'", t);
	        }
	        if(!loc.exists()) {
	            throw new CREIOException(loc.getAbsolutePath() + "Doesn't exists", t);
	        }
	        if(loc.isDirectory()) {
		        //noinspection ResultOfMethodCallIgnored
		        loc.renameTo(new File(loc.getParent() + File.pathSeparator + args[1].val() + File.pathSeparator));
	        } else if(loc.isFile()) {
		        //noinspection ResultOfMethodCallIgnored
		        loc.renameTo(new File(loc.getParent() + File.pathSeparator + args[1].val()));
	        }
	        return CVoid.VOID;
	    }

	    @Override
	    public String getName() {
	        return "rename_file";
	    }

	    @Override
	    public Integer[] numArgs() {
	        return new Integer[]{1};
	    }

	    @Override
	    public String docs() {
	        return "{file, name} void rename a file.";
	    }

	    @Override
	    public Version since() {
	        return new SimpleVersion(1, 0, 0);
	    }
	}
	
	@api
	public static class write_file extends AbstractFunction {
	    @SuppressWarnings("unchecked")
	    @Override
	    public Class<? extends CREThrowable>[] thrown() {
	        return new Class[]{
	                CREIOException.class,
	                CRESecurityException.class
	        };
	    }

	    @Override
	    public boolean isRestricted() {
	        return true;
	    }

	    
	    public Boolean runAsync() {
	        return true;
	    }

	    @Override
	    public Construct exec(Target t, Environment env, Construct... args) throws ConfigRuntimeException {
	        File loc = new File(t.file().getParentFile(), args[0].val());
	        if(!Security.CheckSecurity(loc)) {
	            throw new CRESecurityException("You do not have permission to access the file '" + loc.getAbsolutePath() + "'", t);
	        }
	        try {
	            if(!loc.exists()) {
	                throw new CREIOException(loc.getAbsolutePath() + "Doesn't exists", t);
	            }
	                if(args.length == 3 && args[2].val().equalsIgnoreCase("OVERWRITE")) {
	                    FileUtil.write(args[1].val(), loc, 0);
	                } else {
	                    FileUtil.write(args[1].val(), loc, 1);
	                }

	            return CVoid.VOID;
	        } catch (IOException e) {
	            throw new CREIOException("File could not be created.", t);
	        }
	    }

	    @Override
	    public String getName() {
	        return "write_file";
	    }

	    @Override
	    public Integer[] numArgs() {
	        return new Integer[]{2, 3};
	    }

	    @Override
	    public String docs() {
	        return "{file, string, [mode]} void write string in file. mode is optional, can be OVERWRITE or APPEND";
	    }

	    @Override
	    public Version since() {
	        return new SimpleVersion(1, 0, 0);
	    }
	}
}
