/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recaudacionMunicipio.auth;

/**
 *
 * @author Oscar
 */
public class JwtConfig {

    public static final String LLAVE_SECRETA="IDs.Administr@ciOn22";
    
public static final String RSA_PRIVADA="-----BEGIN OPENSSH PRIVATE KEY-----\n" +
"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\n" +
"o5L5DTHNZpJnTzFQ88Lk08wV7Yg=\n" +
"-----END OPENSSH PRIVATE KEY-----";
public static final String RSA_PUBLICA   ="AAAAB3NzaC1yc2EAAAADAQABAAABgQC+DdYOYSPLNJSDpXVwZnFHxATB4UMjOWpgX/2bfhuQkVinmL+2HqaoR7jcblHyCFddo/14Tpb3uBNYVQ6K5zN/HMMbm6kxN2SPhdRZJ1cEEw5W1Tr63DI/+1aWkQRzhAWAIrWXiYs/cV25BIsGeKDu9ejOUsv28p6iedpmYE/CJlT3r9qG9P1dxf4Rx8aN24wEJ/QRsPTTPgzpuCEHIKu1skNUZLHnGHayKVIZ3+CP+PJkajxsU2gp9Di0mLwMk7bqSy4EW+DqIPNoBXMpGQqBneZoOjR2ylL7Tr+LCmvmOsO+cfKHUa1WjMW+EbDDiT5zAyMMi3+6RGJO1HnHKg2QURInawzZr5SPRpABjkOoiYbaKrUxZs/595LdEbdym3OFqIJYBCXxfbZuenRd2iCiEvu7B5dzI+S+oi3YZ3qthO3gOSxVySkzMKiTj6cl9BJIKMC8dAmcuV0mY7VIp4QfEHDcVhVUcfToFqxnLMVXHLn6RfFsBzgZOhknwft5+Ls= oscar@battysaurios";



}
