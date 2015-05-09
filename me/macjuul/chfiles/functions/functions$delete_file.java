/*   1:    */ package me.macjuul.chfiles.functions;
/*   2:    */ 
/*   3:    */ import com.laytonsmith.PureUtilities.Version;
/*   4:    */ import com.laytonsmith.annotations.api;
/*   5:    */ import com.laytonsmith.core.CHVersion;
/*   6:    */ import com.laytonsmith.core.Security;
/*   7:    */ import com.laytonsmith.core.Static;
/*   8:    */ import com.laytonsmith.core.constructs.CVoid;
/*   9:    */ import com.laytonsmith.core.constructs.Construct;
/*  10:    */ import com.laytonsmith.core.constructs.Target;
/*  11:    */ import com.laytonsmith.core.environments.Environment;
/*  12:    */ import com.laytonsmith.core.exceptions.ConfigRuntimeException;
/*  13:    */ import com.laytonsmith.core.functions.AbstractFunction;
/*  14:    */ import com.laytonsmith.core.functions.Exceptions.ExceptionType;
/*  15:    */ import java.io.File;
/*  16:    */ import java.util.logging.Level;
/*  17:    */ import java.util.logging.Logger;
/*  18:    */ import org.apache.commons.io.FileUtils;
/*  19:    */ 
/*  20:    */ @api
/*  21:    */ public class functions$delete_file
/*  22:    */   extends AbstractFunction
/*  23:    */ {
/*  24:    */   public Exceptions.ExceptionType[] thrown()
/*  25:    */   {
/*  26:102 */     return new Exceptions.ExceptionType[] { Exceptions.ExceptionType.IOException, Exceptions.ExceptionType.SecurityException };
/*  27:    */   }
/*  28:    */   
/*  29:    */   public boolean isRestricted()
/*  30:    */   {
/*  31:107 */     return true;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public Boolean runAsync()
/*  35:    */   {
/*  36:112 */     return Boolean.valueOf(true);
/*  37:    */   }
/*  38:    */   
/*  39:    */   public Construct exec(Target t, Environment environment, Construct... args)
/*  40:    */     throws ConfigRuntimeException
/*  41:    */   {
/*  42:118 */     String location = args[0].val();
/*  43:119 */     location = new File(t.file().getParentFile(), location).getAbsolutePath();
/*  44:120 */     if (!Security.CheckSecurity(location)) {
/*  45:121 */       throw new ConfigRuntimeException("You do not have permission to access the file '" + location + "'", Exceptions.ExceptionType.SecurityException, t);
/*  46:    */     }
/*  47:    */     try
/*  48:    */     {
/*  49:125 */       File file = new File(location);
/*  50:126 */       if (!file.exists()) {
/*  51:127 */         throw new ConfigRuntimeException(file.getAbsolutePath() + "Doesnt Exist", Exceptions.ExceptionType.IOException, t);
/*  52:    */       }
/*  53:129 */       if (file.isDirectory()) {
/*  54:130 */         FileUtils.deleteDirectory(file);
/*  55:    */       } else {
/*  56:132 */         FileUtils.forceDelete(file);
/*  57:    */       }
/*  58:134 */       return CVoid.VOID;
/*  59:    */     }
/*  60:    */     catch (Exception ex)
/*  61:    */     {
/*  62:138 */       Static.getLogger().log(Level.SEVERE, "Could not delete the file while attempting to find " + new File(location).getAbsolutePath() + "\nFile " + (new File(location).exists() ? "exists" : "does not exist"));
/*  63:    */       
/*  64:140 */       throw new ConfigRuntimeException("File could not be deleted.", Exceptions.ExceptionType.IOException, t);
/*  65:    */     }
/*  66:    */   }
/*  67:    */   
/*  68:    */   public String getName()
/*  69:    */   {
/*  70:146 */     return "delete_file";
/*  71:    */   }
/*  72:    */   
/*  73:    */   public Integer[] numArgs()
/*  74:    */   {
/*  75:151 */     return new Integer[] { Integer.valueOf(1) };
/*  76:    */   }
/*  77:    */   
/*  78:    */   public String docs()
/*  79:    */   {
/*  80:156 */     return " ";
/*  81:    */   }
/*  82:    */   
/*  83:    */   public Version since()
/*  84:    */   {
/*  85:161 */     return CHVersion.V3_3_1;
/*  86:    */   }
/*  87:    */ }


/* Location:           D:\Users\julian\Documents\Files\eclipse\projects\CHFiles-1.0.3-SNAPSHOT.jar
 * Qualified Name:     me.macjuul.chfiles.functions.functions.delete_file
 * JD-Core Version:    0.7.0.1
 */