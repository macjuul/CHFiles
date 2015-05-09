/*  1:   */ package me.macjuul.chfiles.functions;
/*  2:   */ 
/*  3:   */ import com.laytonsmith.PureUtilities.Common.FileUtil;
/*  4:   */ import com.laytonsmith.PureUtilities.Version;
/*  5:   */ import com.laytonsmith.annotations.api;
/*  6:   */ import com.laytonsmith.core.CHVersion;
/*  7:   */ import com.laytonsmith.core.Security;
/*  8:   */ import com.laytonsmith.core.Static;
/*  9:   */ import com.laytonsmith.core.constructs.CVoid;
/* 10:   */ import com.laytonsmith.core.constructs.Construct;
/* 11:   */ import com.laytonsmith.core.constructs.Target;
/* 12:   */ import com.laytonsmith.core.environments.Environment;
/* 13:   */ import com.laytonsmith.core.exceptions.ConfigRuntimeException;
/* 14:   */ import com.laytonsmith.core.functions.AbstractFunction;
/* 15:   */ import com.laytonsmith.core.functions.Exceptions.ExceptionType;
/* 16:   */ import java.io.File;
/* 17:   */ import java.util.logging.Level;
/* 18:   */ import java.util.logging.Logger;
/* 19:   */ 
/* 20:   */ @api
/* 21:   */ public class functions$create_file
/* 22:   */   extends AbstractFunction
/* 23:   */ {
/* 24:   */   public Exceptions.ExceptionType[] thrown()
/* 25:   */   {
/* 26:37 */     return new Exceptions.ExceptionType[] { Exceptions.ExceptionType.IOException, Exceptions.ExceptionType.SecurityException };
/* 27:   */   }
/* 28:   */   
/* 29:   */   public boolean isRestricted()
/* 30:   */   {
/* 31:42 */     return true;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public Boolean runAsync()
/* 35:   */   {
/* 36:47 */     return Boolean.valueOf(true);
/* 37:   */   }
/* 38:   */   
/* 39:   */   public Construct exec(Target t, Environment environment, Construct... args)
/* 40:   */     throws ConfigRuntimeException
/* 41:   */   {
/* 42:53 */     String location = args[0].val();
/* 43:54 */     location = new File(t.file().getParentFile(), location).getAbsolutePath();
/* 44:55 */     if (!Security.CheckSecurity(location)) {
/* 45:56 */       throw new ConfigRuntimeException("You do not have permission to access the file '" + location + "'", Exceptions.ExceptionType.SecurityException, t);
/* 46:   */     }
/* 47:   */     try
/* 48:   */     {
/* 49:60 */       File file = new File(location);
/* 50:61 */       if (file.exists()) {
/* 51:62 */         throw new ConfigRuntimeException(file.getAbsolutePath() + "Already Exists", Exceptions.ExceptionType.IOException, t);
/* 52:   */       }
/* 53:64 */       FileUtil.write("", file, true);
/* 54:65 */       return CVoid.VOID;
/* 55:   */     }
/* 56:   */     catch (Exception ex)
/* 57:   */     {
/* 58:69 */       Static.getLogger().log(Level.SEVERE, "Could not be created while attempting to find " + new File(location).getAbsolutePath() + "\nFile " + (new File(location).exists() ? "exists" : "does not exist"));
/* 59:   */       
/* 60:71 */       throw new ConfigRuntimeException("File could not be created.", Exceptions.ExceptionType.IOException, t);
/* 61:   */     }
/* 62:   */   }
/* 63:   */   
/* 64:   */   public String getName()
/* 65:   */   {
/* 66:77 */     return "create_file";
/* 67:   */   }
/* 68:   */   
/* 69:   */   public Integer[] numArgs()
/* 70:   */   {
/* 71:82 */     return new Integer[] { Integer.valueOf(1) };
/* 72:   */   }
/* 73:   */   
/* 74:   */   public String docs()
/* 75:   */   {
/* 76:87 */     return " ";
/* 77:   */   }
/* 78:   */   
/* 79:   */   public Version since()
/* 80:   */   {
/* 81:92 */     return CHVersion.V3_3_1;
/* 82:   */   }
/* 83:   */ }


/* Location:           D:\Users\julian\Documents\Files\eclipse\projects\CHFiles-1.0.3-SNAPSHOT.jar
 * Qualified Name:     me.macjuul.chfiles.functions.functions.create_file
 * JD-Core Version:    0.7.0.1
 */