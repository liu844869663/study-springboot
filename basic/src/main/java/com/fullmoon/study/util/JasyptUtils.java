package com.fullmoon.study.util;

import com.ulisesbocchio.jasyptspringboot.properties.JasyptEncryptorConfigurationProperties;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI;

import java.util.UUID;

/**
 * @author jingping.liu
 * @date 2020-11-20
 * @description Jasypt 安全框架加密类工具包
 */
public class JasyptUtils {

    /**
     * 生成一个 {@link PBEConfig} 配置对象
     * <p>
     * 注意！！！
     * 可查看 Jasypt 全局配置对象 {@link JasyptEncryptorConfigurationProperties} 中的默认值
     * 这里的配置建议与默认值保持一致，否则需要在 application.yml 中定义这里的配置（也可以通过 JVM 参数的方法）
     * 注意 password 和 algorithm 配置项，如果不一致在启动时可能会解密失败而报错
     *
     * @param salt 盐值
     * @return SimpleStringPBEConfig 加密配置
     */
    private static SimpleStringPBEConfig generatePBEConfig(String salt) {
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        // 设置 salt 盐值
        config.setPassword(salt);
        // 设置要创建的加密程序池的大小，这里仅临时创建一个，设置 1 即可
        config.setPoolSize("1");
        // 设置加密算法， 此算法必须由 JCE 提供程序支持，默认值 PBEWITHHMACSHA512ANDAES_256
        config.setAlgorithm("PBEWithMD5AndDES");
        // 设置应用于获取加密密钥的哈希迭代次数
        config.setKeyObtentionIterations("1000");
        // 设置要请求加密算法的安全提供程序的名称
        config.setProviderName("SunJCE");
        // 设置 salt 盐的生成器
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        // 设置 IV 生成器
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        // 设置字符串输出的编码形式，支持 base64 和 hexadecimal
        config.setStringOutputType("hexadecimal");
        return config;
    }

    /**
     * 通过 {@link PooledPBEStringEncryptor} 进行加密密
     *
     * @param salt 盐值
     * @param message 需要加密的内容
     * @return 加密后的内容
     */
    public static String encrypt(String salt, String message) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig pbeConfig = generatePBEConfig(salt);
        // 生成加密配置
        encryptor.setConfig(pbeConfig);
        System.out.println("----ARGUMENTS-------------------");
        System.out.println("message: " + message);
        System.out.println("salt: " + pbeConfig.getPassword());
        System.out.println("algorithm: " + pbeConfig.getAlgorithm());
        System.out.println("stringOutputType: " + pbeConfig.getStringOutputType());
        // 进行加密
        String cipherText = encryptor.encrypt(message);
        System.out.println("----OUTPUT-------------------");
        System.out.println(cipherText);
        return cipherText;
    }

    public static String decrypt(String salt, String message) {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        encryptor.setConfig(generatePBEConfig(salt));
        return encryptor.decrypt(message);
    }

    public static void main(String[] args) {
        // 随机生成一个 salt 盐值
        String salt = UUID.randomUUID().toString().replace("-", "");
        // 进行加密
        encrypt(salt, "admin@951753");
    }
}
