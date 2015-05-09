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
/*  21:    */ public class functions$copy_file
/*  22:    */   extends AbstractFunction
/*  23:    */ {
/*  24:    */   public Exceptions.ExceptionType[] thrown()
/*  25:    */   {
/*  26:248 */     return new Exceptions.ExceptionType[] { Exceptions.ExceptionType.FormatException, Exceptions.ExceptionType.IOException, Exceptions.ExceptionType.SecurityException };
/*  27:    */   }
/*  28:    */   
/*  29:    */   public boolean isRestricted()
/*  30:    */   {
/*  31:253 */     return true;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public Boolean runAsync()
/*  35:    */   {
/*  36:258 */     return Boolean.valueOf(true);
/*  37:    */   }
/*  38:    */   
/*  39:    */   public Construct exec(Target t, Environment environment, Construct... args)
/*  40:    */     throws ConfigRuntimeException
/*  41:    */   {
/*  42:264 */     String fromLocation = args[0].val();
/*  43:265 */     String toLocation = args[1].val();
/*  44:266 */     fromLocation = new File(t.file().getParentFile(), fromLocation).getAbsolutePath();
/*  45:267 */     toLocation = new File(t.file().getParentFile(), toLocation).getAbsolutePath();
/*  46:268 */     if ((!Security.CheckSecurity(toLocation)) || (!Security.CheckSecurity(fromLocation))) {
/*  47:269 */       throw new ConfigRuntimeException("You do not have access to some of the files", Exceptions.ExceptionType.SecurityException, t);
/*  48:    */     }
/*  49:271 */     File fromFile = new File(fromLocation);
/*  50:272 */     File toFile = new File(toLocation);
/*  51:    */     try
/*  52:    */     {
/*  53:275 */       if (fromFile.isDirectory()) {
/*  54:276 */         FileUtils.copyDirectory(fromFile, toFile);
/*  55:    */       } else {
/*  56:278 */         FileUtils.copyFile(fromFile, toFile);
/*  57:    */       }
/*  58:280 */       return CVoid.VOID;
/*  59:    */     }
/*  60:    */     catch (Exception ex)
/*  61:    */     {
/*  62:284 */       Static.getLogger().log(Level.SEVERE, "Could not write in file while attempting to find " + new File(fromLocation).getAbsolutePath() + "\nFile " + (new File(fromLocation).exists() ? "exists" : "does not exist"));
/*  63:    */       
/*  64:286 */       throw new ConfigRuntimeException("File could not be written in.", Exceptions.ExceptionType.IOException, t);
/*  65:    */     }
/*  66:    */   }
/*  67:    */   
/*  68:    */   public String getName()
/*  69:    */   {
/*  70:292 */     return "copy_file";
/*  71:    */   }
/*  72:    */   
/*  73:    */   public Integer[] numArgs()
/*  74:    */   {
/*  75:297 */     return new Integer[] { Integer.valueOf(2) };
/*  76:    */   }
/*  77:    */   
/*  78:    */   public String docs()
/*  79:    */   {
/*  80:302 */     return " ";
/*  81:    */   }
/*  82:    */   
/*  83:    */   public Version since()
/*  84:    */   {
/*  85:307 */     return CHVersion.V3_3_1;
/*  86:    */   }
/*  87:    */ }


/* Location:           D:\Users\julian\Documents\Files\eclipse\projects\CHFiles-1.0.3-SNAPSHOT.jar
 * Qualified Name:     me.macjuul.chfiles.functions.functions.copy_file
 * JD-Core Version:    0.7.0.1
 */