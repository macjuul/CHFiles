package me.macjuul.chfiles.Functions;

import com.laytonsmith.PureUtilities.SimpleVersion;
import com.laytonsmith.PureUtilities.Version;
import com.laytonsmith.annotations.api;
import com.laytonsmith.core.Security;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.environments.Environment;
import com.laytonsmith.core.exceptions.CRE.CREIOException;
import com.laytonsmith.core.exceptions.CRE.CRESecurityException;
import com.laytonsmith.core.exceptions.CRE.CREThrowable;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.AbstractFunction;

import java.io.File;

/**
 * Created by bexco on 2016-03-16.
 */
@api
public class list_files extends AbstractFunction {
    @Override
    public Class<? extends CREThrowable>[] thrown() {
        return new Class[]{
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
            for(String file:list) {
                ret.push(new CString(file, t), t);
            }
        } else {
            throw new CREIOException("This path is not directory.", t);
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