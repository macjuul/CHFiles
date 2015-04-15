/*   1:    */ package me.macjuul.chfiles.functions;
/*   2:    */ 
/*   3:    */ import com.laytonsmith.PureUtilities.Common.FileUtil;
/*   4:    */ import com.laytonsmith.PureUtilities.Version;
/*   5:    */ import com.laytonsmith.annotations.api;
/*   6:    */ import com.laytonsmith.core.CHVersion;
/*   7:    */ import com.laytonsmith.core.Security;
/*   8:    */ import com.laytonsmith.core.Static;
/*   9:    */ import com.laytonsmith.core.constructs.CArray;
/*  10:    */ import com.laytonsmith.core.constructs.CString;
/*  11:    */ import com.laytonsmith.core.constructs.CVoid;
/*  12:    */ import com.laytonsmith.core.constructs.Construct;
/*  13:    */ import com.laytonsmith.core.constructs.Target;
/*  14:    */ import com.laytonsmith.core.environments.Environment;
/*  15:    */ import com.laytonsmith.core.exceptions.ConfigRuntimeException;
/*  16:    */ import com.laytonsmith.core.functions.AbstractFunction;
/*  17:    */ import com.laytonsmith.core.functions.Exceptions.ExceptionType;
/*  18:    */ import java.io.File;
/*  19:    */ import java.util.logging.Level;
/*  20:    */ import java.util.logging.Logger;
/*  21:    */ import org.apache.commons.io.FileUtils;
/*  22:    */ 
/*  23:    */ public class functions
/*  24:    */ {
/*  25:    */   public static String docs()
/*  26:    */   {
/*  27: 28 */     return "This Extension will add some sick things to CommandHelper!";
/*  28:    */   }
/*  29:    */   
/*  30:    */   @api
/*  31:    */   public static class create_file
/*  32:    */     extends AbstractFunction
/*  33:    */   {
/*  34:    */     public Exceptions.ExceptionType[] thrown()
/*  35:    */     {
/*  36: 37 */       return new Exceptions.ExceptionType[] { Exceptions.ExceptionType.IOException, Exceptions.ExceptionType.SecurityException };
/*  37:    */     }
/*  38:    */     
/*  39:    */     public boolean isRestricted()
/*  40:    */     {
/*  41: 42 */       return true;
/*  42:    */     }
/*  43:    */     
/*  44:    */     public Boolean runAsync()
/*  45:    */     {
/*  46: 47 */       return Boolean.valueOf(true);
/*  47:    */     }
/*  48:    */     
/*  49:    */     public Construct exec(Target t, Environment environment, Construct... args)
/*  50:    */       throws ConfigRuntimeException
/*  51:    */     {
/*  52: 53 */       String location = args[0].val();
/*  53: 54 */       location = new File(t.file().getParentFile(), location).getAbsolutePath();
/*  54: 55 */       if (!Security.CheckSecurity(location)) {
/*  55: 56 */         throw new ConfigRuntimeException("You do not have permission to access the file '" + location + "'", Exceptions.ExceptionType.SecurityException, t);
/*  56:    */       }
/*  57:    */       try
/*  58:    */       {
/*  59: 60 */         File file = new File(location);
/*  60: 61 */         if (file.exists()) {
/*  61: 62 */           throw new ConfigRuntimeException(file.getAbsolutePath() + "Already Exists", Exceptions.ExceptionType.IOException, t);
/*  62:    */         }
/*  63: 64 */         FileUtil.write("", file, true);
/*  64: 65 */         return CVoid.VOID;
/*  65:    */       }
/*  66:    */       catch (Exception ex)
/*  67:    */       {
/*  68: 69 */         Static.getLogger().log(Level.SEVERE, "Could not be created while attempting to find " + new File(location).getAbsolutePath() + "\nFile " + (new File(location).exists() ? "exists" : "does not exist"));
/*  69:    */         
/*  70: 71 */         throw new ConfigRuntimeException("File could not be created.", Exceptions.ExceptionType.IOException, t);
/*  71:    */       }
/*  72:    */     }
/*  73:    */     
/*  74:    */     public String getName()
/*  75:    */     {
/*  76: 77 */       return "create_file";
/*  77:    */     }
/*  78:    */     
/*  79:    */     public Integer[] numArgs()
/*  80:    */     {
/*  81: 82 */       return new Integer[] { Integer.valueOf(1) };
/*  82:    */     }
/*  83:    */     
/*  84:    */     public String docs()
/*  85:    */     {
/*  86: 87 */       return " ";
/*  87:    */     }
/*  88:    */     
/*  89:    */     public Version since()
/*  90:    */     {
/*  91: 92 */       return CHVersion.V3_3_1;
/*  92:    */     }
/*  93:    */   }
/*  94:    */   
/*  95:    */   @api
/*  96:    */   public static class delete_file
/*  97:    */     extends AbstractFunction
/*  98:    */   {
/*  99:    */     public Exceptions.ExceptionType[] thrown()
/* 100:    */     {
/* 101:102 */       return new Exceptions.ExceptionType[] { Exceptions.ExceptionType.IOException, Exceptions.ExceptionType.SecurityException };
/* 102:    */     }
/* 103:    */     
/* 104:    */     public boolean isRestricted()
/* 105:    */     {
/* 106:107 */       return true;
/* 107:    */     }
/* 108:    */     
/* 109:    */     public Boolean runAsync()
/* 110:    */     {
/* 111:112 */       return Boolean.valueOf(true);
/* 112:    */     }
/* 113:    */     
/* 114:    */     public Construct exec(Target t, Environment environment, Construct... args)
/* 115:    */       throws ConfigRuntimeException
/* 116:    */     {
/* 117:118 */       String location = args[0].val();
/* 118:119 */       location = new File(t.file().getParentFile(), location).getAbsolutePath();
/* 119:120 */       if (!Security.CheckSecurity(location)) {
/* 120:121 */         throw new ConfigRuntimeException("You do not have permission to access the file '" + location + "'", Exceptions.ExceptionType.SecurityException, t);
/* 121:    */       }
/* 122:    */       try
/* 123:    */       {
/* 124:125 */         File file = new File(location);
/* 125:126 */         if (!file.exists()) {
/* 126:127 */           throw new ConfigRuntimeException(file.getAbsolutePath() + "Doesnt Exist", Exceptions.ExceptionType.IOException, t);
/* 127:    */         }
/* 128:129 */         if (file.isDirectory()) {
/* 129:130 */           FileUtils.deleteDirectory(file);
/* 130:    */         } else {
/* 131:132 */           FileUtils.forceDelete(file);
/* 132:    */         }
/* 133:134 */         return CVoid.VOID;
/* 134:    */       }
/* 135:    */       catch (Exception ex)
/* 136:    */       {
/* 137:138 */         Static.getLogger().log(Level.SEVERE, "Could not delete the file while attempting to find " + new File(location).getAbsolutePath() + "\nFile " + (new File(location).exists() ? "exists" : "does not exist"));
/* 138:    */         
/* 139:140 */         throw new ConfigRuntimeException("File could not be deleted.", Exceptions.ExceptionType.IOException, t);
/* 140:    */       }
/* 141:    */     }
/* 142:    */     
/* 143:    */     public String getName()
/* 144:    */     {
/* 145:146 */       return "delete_file";
/* 146:    */     }
/* 147:    */     
/* 148:    */     public Integer[] numArgs()
/* 149:    */     {
/* 150:151 */       return new Integer[] { Integer.valueOf(1) };
/* 151:    */     }
/* 152:    */     
/* 153:    */     public String docs()
/* 154:    */     {
/* 155:156 */       return " ";
/* 156:    */     }
/* 157:    */     
/* 158:    */     public Version since()
/* 159:    */     {
/* 160:161 */       return CHVersion.V3_3_1;
/* 161:    */     }
/* 162:    */   }
/* 163:    */   
/* 164:    */   @api
/* 165:    */   public static class write_file
/* 166:    */     extends AbstractFunction
/* 167:    */   {
/* 168:    */     public Exceptions.ExceptionType[] thrown()
/* 169:    */     {
/* 170:171 */       return new Exceptions.ExceptionType[] { Exceptions.ExceptionType.FormatException, Exceptions.ExceptionType.IOException, Exceptions.ExceptionType.SecurityException };
/* 171:    */     }
/* 172:    */     
/* 173:    */     public boolean isRestricted()
/* 174:    */     {
/* 175:176 */       return true;
/* 176:    */     }
/* 177:    */     
/* 178:    */     public Boolean runAsync()
/* 179:    */     {
/* 180:181 */       return Boolean.valueOf(true);
/* 181:    */     }
/* 182:    */     
/* 183:    */     public Construct exec(Target t, Environment environment, Construct... args)
/* 184:    */       throws ConfigRuntimeException
/* 185:    */     {
/* 186:187 */       String location = args[0].val();
/* 187:188 */       String content = args[1].val();
/* 188:189 */       int mode = 0;
/* 189:190 */       if (args.length == 3)
/* 190:    */       {
/* 191:192 */         String type = args[2].val().toUpperCase();
/* 192:193 */         if ((type.equals("OVERWRITE")) || (type.equals("APPEND")))
/* 193:    */         {
/* 194:195 */           if (type.equals("APPEND")) {
/* 195:196 */             mode = 1;
/* 196:    */           }
/* 197:    */         }
/* 198:    */         else {
/* 199:200 */           throw new ConfigRuntimeException("Argument 3 of write_file is invalid:" + args[3].val(), Exceptions.ExceptionType.FormatException, t);
/* 200:    */         }
/* 201:    */       }
/* 202:203 */       location = new File(t.file().getParentFile(), location).getAbsolutePath();
/* 203:204 */       if (!Security.CheckSecurity(location)) {
/* 204:205 */         throw new ConfigRuntimeException("You do not have permission to access the file '" + location + "'", Exceptions.ExceptionType.SecurityException, t);
/* 205:    */       }
/* 206:    */       try
/* 207:    */       {
/* 208:209 */         File file = new File(location);
/* 209:210 */         FileUtil.write(content, file, mode, false);
/* 210:211 */         return CVoid.VOID;
/* 211:    */       }
/* 212:    */       catch (Exception ex)
/* 213:    */       {
/* 214:215 */         Static.getLogger().log(Level.SEVERE, "Could not write in file while attempting to find " + new File(location).getAbsolutePath() + "\nFile " + (new File(location).exists() ? "exists" : "does not exist"));
/* 215:    */         
/* 216:217 */         throw new ConfigRuntimeException("File could not be written in.", Exceptions.ExceptionType.IOException, t);
/* 217:    */       }
/* 218:    */     }
/* 219:    */     
/* 220:    */     public String getName()
/* 221:    */     {
/* 222:223 */       return "write_file";
/* 223:    */     }
/* 224:    */     
/* 225:    */     public Integer[] numArgs()
/* 226:    */     {
/* 227:228 */       return new Integer[] { Integer.valueOf(2), Integer.valueOf(3) };
/* 228:    */     }
/* 229:    */     
/* 230:    */     public String docs()
/* 231:    */     {
/* 232:233 */       return " ";
/* 233:    */     }
/* 234:    */     
/* 235:    */     public Version since()
/* 236:    */     {
/* 237:238 */       return CHVersion.V3_3_1;
/* 238:    */     }
/* 239:    */   }
/* 240:    */   
/* 241:    */   @api
/* 242:    */   public static class copy_file
/* 243:    */     extends AbstractFunction
/* 244:    */   {
/* 245:    */     public Exceptions.ExceptionType[] thrown()
/* 246:    */     {
/* 247:248 */       return new Exceptions.ExceptionType[] { Exceptions.ExceptionType.FormatException, Exceptions.ExceptionType.IOException, Exceptions.ExceptionType.SecurityException };
/* 248:    */     }
/* 249:    */     
/* 250:    */     public boolean isRestricted()
/* 251:    */     {
/* 252:253 */       return true;
/* 253:    */     }
/* 254:    */     
/* 255:    */     public Boolean runAsync()
/* 256:    */     {
/* 257:258 */       return Boolean.valueOf(true);
/* 258:    */     }
/* 259:    */     
/* 260:    */     public Construct exec(Target t, Environment environment, Construct... args)
/* 261:    */       throws ConfigRuntimeException
/* 262:    */     {
/* 263:264 */       String fromLocation = args[0].val();
/* 264:265 */       String toLocation = args[1].val();
/* 265:266 */       fromLocation = new File(t.file().getParentFile(), fromLocation).getAbsolutePath();
/* 266:267 */       toLocation = new File(t.file().getParentFile(), toLocation).getAbsolutePath();
/* 267:268 */       if ((!Security.CheckSecurity(toLocation)) || (!Security.CheckSecurity(fromLocation))) {
/* 268:269 */         throw new ConfigRuntimeException("You do not have access to some of the files", Exceptions.ExceptionType.SecurityException, t);
/* 269:    */       }
/* 270:271 */       File fromFile = new File(fromLocation);
/* 271:272 */       File toFile = new File(toLocation);
/* 272:    */       try
/* 273:    */       {
/* 274:275 */         if (fromFile.isDirectory()) {
/* 275:276 */           FileUtils.copyDirectory(fromFile, toFile);
/* 276:    */         } else {
/* 277:278 */           FileUtils.copyFile(fromFile, toFile);
/* 278:    */         }
/* 279:280 */         return CVoid.VOID;
/* 280:    */       }
/* 281:    */       catch (Exception ex)
/* 282:    */       {
/* 283:284 */         Static.getLogger().log(Level.SEVERE, "Could not write in file while attempting to find " + new File(fromLocation).getAbsolutePath() + "\nFile " + (new File(fromLocation).exists() ? "exists" : "does not exist"));
/* 284:    */         
/* 285:286 */         throw new ConfigRuntimeException("File could not be written in.", Exceptions.ExceptionType.IOException, t);
/* 286:    */       }
/* 287:    */     }
/* 288:    */     
/* 289:    */     public String getName()
/* 290:    */     {
/* 291:292 */       return "copy_file";
/* 292:    */     }
/* 293:    */     
/* 294:    */     public Integer[] numArgs()
/* 295:    */     {
/* 296:297 */       return new Integer[] { Integer.valueOf(2) };
/* 297:    */     }
/* 298:    */     
/* 299:    */     public String docs()
/* 300:    */     {
/* 301:302 */       return " ";
/* 302:    */     }
/* 303:    */     
/* 304:    */     public Version since()
/* 305:    */     {
/* 306:307 */       return CHVersion.V3_3_1;
/* 307:    */     }
/* 308:    */   }
/* 309:    */   
/* 310:    */   @api
/* 311:    */   public static class rename_file
/* 312:    */     extends AbstractFunction
/* 313:    */   {
/* 314:    */     public Exceptions.ExceptionType[] thrown()
/* 315:    */     {
/* 316:317 */       return new Exceptions.ExceptionType[] { Exceptions.ExceptionType.FormatException, Exceptions.ExceptionType.IOException, Exceptions.ExceptionType.SecurityException };
/* 317:    */     }
/* 318:    */     
/* 319:    */     public boolean isRestricted()
/* 320:    */     {
/* 321:322 */       return true;
/* 322:    */     }
/* 323:    */     
/* 324:    */     public Boolean runAsync()
/* 325:    */     {
/* 326:327 */       return Boolean.valueOf(true);
/* 327:    */     }
/* 328:    */     
/* 329:    */     public Construct exec(Target t, Environment environment, Construct... args)
/* 330:    */       throws ConfigRuntimeException
/* 331:    */     {
/* 332:333 */       String location = args[0].val();
/* 333:334 */       String name = args[1].val();
/* 334:335 */       location = new File(t.file().getParentFile(), location).getAbsolutePath();
/* 335:336 */       if (!Security.CheckSecurity(location)) {
/* 336:337 */         throw new ConfigRuntimeException("You do not have permission to access the file '" + location + "'", Exceptions.ExceptionType.SecurityException, t);
/* 337:    */       }
/* 338:    */       try
/* 339:    */       {
/* 340:341 */         File file = new File(location);
/* 341:    */         
/* 342:343 */         File dir = file.getParentFile();
/* 343:344 */         File rename = new File(dir, name);
/* 344:346 */         if (rename.exists()) {
/* 345:346 */           throw new ConfigRuntimeException("File already exists!", Exceptions.ExceptionType.IOException, t);
/* 346:    */         }
/* 347:348 */         Boolean success = Boolean.valueOf(file.renameTo(rename));
/* 348:349 */         if (!success.booleanValue()) {
/* 349:350 */           throw new ConfigRuntimeException("Unable to rename file!", Exceptions.ExceptionType.IOException, t);
/* 350:    */         }
/* 351:352 */         return CVoid.VOID;
/* 352:    */       }
/* 353:    */       catch (Exception ex)
/* 354:    */       {
/* 355:356 */         Static.getLogger().log(Level.SEVERE, "Could not write in file while attempting to find " + new File(location).getAbsolutePath() + "\nFile " + (new File(location).exists() ? "exists" : "does not exist"));
/* 356:    */         
/* 357:358 */         throw new ConfigRuntimeException("File could not be written in.", Exceptions.ExceptionType.IOException, t);
/* 358:    */       }
/* 359:    */     }
/* 360:    */     
/* 361:    */     public String getName()
/* 362:    */     {
/* 363:364 */       return "rename_file";
/* 364:    */     }
/* 365:    */     
/* 366:    */     public Integer[] numArgs()
/* 367:    */     {
/* 368:369 */       return new Integer[] { Integer.valueOf(2) };
/* 369:    */     }
/* 370:    */     
/* 371:    */     public String docs()
/* 372:    */     {
/* 373:374 */       return " ";
/* 374:    */     }
/* 375:    */     
/* 376:    */     public Version since()
/* 377:    */     {
/* 378:379 */       return CHVersion.V3_3_1;
/* 379:    */     }
/* 380:    */   }
/* 381:    */   
/* 382:    */   @api
/* 383:    */   public static class list_files
/* 384:    */     extends AbstractFunction
/* 385:    */   {
/* 386:    */     public Exceptions.ExceptionType[] thrown()
/* 387:    */     {
/* 388:389 */       return new Exceptions.ExceptionType[] { Exceptions.ExceptionType.IOException };
/* 389:    */     }
/* 390:    */     
/* 391:    */     public boolean isRestricted()
/* 392:    */     {
/* 393:394 */       return true;
/* 394:    */     }
/* 395:    */     
/* 396:    */     public Boolean runAsync()
/* 397:    */     {
/* 398:399 */       return Boolean.valueOf(true);
/* 399:    */     }
/* 400:    */     
/* 401:    */     public Construct exec(Target t, Environment environment, Construct... args)
/* 402:    */       throws ConfigRuntimeException
/* 403:    */     {
/* 404:405 */       CArray ret = new CArray(t);
/* 405:406 */       File path = new File(args[0].val());
/* 406:407 */       if (!path.isDirectory()) {
/* 407:408 */         throw new ConfigRuntimeException("Path is not a directory.", Exceptions.ExceptionType.IOException, t);
/* 408:    */       }
/* 409:410 */       String[] list = path.list();
/* 410:411 */       for (String s : list) {
/* 411:412 */         ret.push(new CString(s, t));
/* 412:    */       }
/* 413:414 */       return ret;
/* 414:    */     }
/* 415:    */     
/* 416:    */     public String getName()
/* 417:    */     {
/* 418:419 */       return "list_files";
/* 419:    */     }
/* 420:    */     
/* 421:    */     public Integer[] numArgs()
/* 422:    */     {
/* 423:424 */       return new Integer[] { Integer.valueOf(1) };
/* 424:    */     }
/* 425:    */     
/* 426:    */     public String docs()
/* 427:    */     {
/* 428:429 */       return " ";
/* 429:    */     }
/* 430:    */     
/* 431:    */     public Version since()
/* 432:    */     {
/* 433:434 */       return CHVersion.V3_3_1;
/* 434:    */     }
/* 435:    */   }
/* 436:    */ }


/* Location:           D:\Users\julian\Documents\Files\eclipse\projects\CHFiles-1.0.2-SNAPSHOT.jar
 * Qualified Name:     me.macjuul.chfiles.functions.functions
 * JD-Core Version:    0.7.0.1
 */