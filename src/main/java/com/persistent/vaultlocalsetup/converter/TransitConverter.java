package com.persistent.vaultlocalsetup.converter;

import com.persistent.vaultlocalsetup.service.BeanUtil;
import org.springframework.vault.core.VaultOperations;
import org.springframework.vault.support.Ciphertext;
import org.springframework.vault.support.Plaintext;

import javax.persistence.AttributeConverter;

public class TransitConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String customer) {
        VaultOperations vaultOps = BeanUtil.getBean(VaultOperations.class);
        Plaintext plaintext = Plaintext.of(customer);
        String cipherText = vaultOps.opsForTransit().encrypt("user", plaintext).getCiphertext();
        return cipherText;
    }

    @Override
    public String convertToEntityAttribute(String customer) {
        VaultOperations vaultOps = BeanUtil.getBean(VaultOperations.class);
        Ciphertext ciphertext = Ciphertext.of(customer);
        String plaintext = vaultOps.opsForTransit().decrypt("user", ciphertext).asString();
        return plaintext;
    }

}
