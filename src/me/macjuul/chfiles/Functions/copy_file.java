package me.macjuul.chfiles.Functions;

import java.io.File;
import java.io.IOException;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.Security;
import com.laytonsmith.core.constructs.CVoid;
import com.laytonsmith.core.constructs.Construct;
import com.laytonsmith.core.constructs.Target;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.exceptions.CRE.CREFormatException;
import com.laytonsmith.core.exceptions.CRE.CREIOException;
import com.laytonsmith.core.exceptions.CRE.CRESecurityException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.functions.AbstractFunction;
import com.laytonsmith.libs.org.apache.commons.io.FileUtils;

/**
 * Created by bexco on 2016-03-16.
 */
@api
public class copy_file extends AbstractFunction {
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
