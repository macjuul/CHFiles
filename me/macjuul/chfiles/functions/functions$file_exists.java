/*   1:    */ package me.macjuul.chfiles.functions;
/*   2:    */ 
/*   3:    */ import com.laytonsmith.PureUtilities.Version;
/*   4:    */ import com.laytonsmith.annotations.api;
/*   5:    */ import com.laytonsmith.core.CHVersion;
/*   6:    */ import com.laytonsmith.core.Security;
/*   7:    */ import com.laytonsmith.core.constructs.CBoolean;
/*   8:    */ import com.laytonsmith.core.constructs.Construct;
/*   9:    */ import com.laytonsmith.core.constructs.Target;
/*  10:    */ import com.laytonsmith.core.environments.Environment;
/*  11:    */ import com.laytonsmith.core.exceptions.ConfigRuntimeException;
/*  12:    */ import com.laytonsmith.core.functions.AbstractFunction;
/*  13:    */ import com.laytonsmith.core.functions.Exceptions.ExceptionType;
/*  14:    */ import java.io.File;
/*  15:    */ 
/*  16:    */ @api
/*  17:    */ public class functions$file_exists
/*  18:    */   extends AbstractFunction
/*  19:    */ {
/*  20:    */   public Exceptions.ExceptionType[] thrown()
/*  21:    */   {
/*  22:389 */     return new Exceptions.ExceptionType[] { Exceptions.ExceptionType.IOException };
/*  23:    */   }
/*  24:    */   
/*  25:    */   public boolean isRestricted()
/*  26:    */   {
/*  27:394 */     return true;
/*  28:    */   }
/*  29:    */   
/*  30:    */   public Boolean runAsync()
/*  31:    */   {
/*  32:399 */     return Boolean.valueOf(true);
/*  33:    */   }
/*  34:    */   
/*  35:    */   public Construct exec(Target t, Environment environment, Construct... args)
/*  36:    */     throws ConfigRuntimeException
/*  37:    */   {
/*  38:405 */     File file = new File(args[0].val());
/*  39:406 */     if (!Security.CheckSecurity(file)) {
/*  40:407 */       throw new ConfigRuntimeException("You do not have permission to access the file '" + file.getAbsolutePath() + "'", Exceptions.ExceptionType.SecurityException, t);
/*  41:    */     }
/*  42:409 */     return CBoolean.get(file.exists());
/*  43:    */   }
/*  44:    */   
/*  45:    */   public String getName()
/*  46:    */   {
/*  47:414 */     return "file_exists";
/*  48:    */   }
/*  49:    */   
/*  50:    */   public Integer[] numArgs()
/*  51:    */   {
/*  52:419 */     return new Integer[] { Integer.valueOf(1) };
/*  53:    */   }
/*  54:    */   
/*  55:    */   public String docs()
/*  56:    */   {
/*  57:424 */     return " ";
/*  58:    */   }
/*  59:    */   
/*  60:    */   public Version since()
/*  61:    */   {
/*  62:429 */     return CHVersion.V3_3_1;
/*  63:    */   }
/*  64:    */ }


/* Location:           D:\Users\julian\Documents\Files\eclipse\projects\CHFiles-1.0.3-SNAPSHOT.jar
 * Qualified Name:     me.macjuul.chfiles.functions.functions.file_exists
 * JD-Core Version:    0.7.0.1
 */