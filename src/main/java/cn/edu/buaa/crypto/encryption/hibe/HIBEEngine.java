package cn.edu.buaa.crypto.encryption.hibe;

import cn.edu.buaa.crypto.algebra.Engine;
import cn.edu.buaa.crypto.algebra.serparams.PairingKeySerPair;
import cn.edu.buaa.crypto.algebra.serparams.PairingKeySerParameter;
import cn.edu.buaa.crypto.algebra.serparams.PairingCipherSerParameter;
import it.unisa.dia.gas.jpbc.Element;
import it.unisa.dia.gas.jpbc.PairingParameters;
import org.bouncycastle.crypto.InvalidCipherTextException;

/**
 * Created by Weiran Liu on 2015/10/5.
 *
 * Hierarchical Identity-Based Encryption Engine.
 * All instances should implement this Interface.
 */
public interface HIBEEngine extends Engine {
    /**
     * Setup Algorithm for HIBE
     * @param pairingParameters Pairing Parameters
     * @param maxDepth maximal depth of hierarchy, ignore if the scheme is unbounded
     * @return public key / master secret key pair of the scheme
     */
    PairingKeySerPair setup(PairingParameters pairingParameters, int maxDepth);

    /**
     * Secret Key Generation Algorithm for HIBE
     * @param publicKey public key
     * @param masterKey master secret key
     * @param ids associated identity vector
     * @return secret key associated with the identity vector ids
     */
    PairingKeySerParameter keyGen(PairingKeySerParameter publicKey, PairingKeySerParameter masterKey, String... ids);

    /**
     * Secret Key Delegation Algorithm for HIBE
     * @param publicKey public key
     * @param secretKey secret key associated with an identity vector ids
     * @param id delegated identity
     * @return secret key associated with the identity vector (ids, id)
     */
    PairingKeySerParameter delegate(PairingKeySerParameter publicKey, PairingKeySerParameter secretKey, String id);

    /**
     * Encryption Algorithm for HIBE
     * @param publicKey public key
     * @param ids an identity vector
     * @param message the message in GT
     * @return ciphertext associated with the identity vector ids
     */
    PairingCipherSerParameter encryption(PairingKeySerParameter publicKey, String[] ids, Element message);

    /**
     * Decryption Algorithm for HIBE
     * @param publicKey public key
     * @param secretKey secret key associated with an identity vector
     * @param ids identity vector associating with the ciphertext
     * @param ciphertext ciphertext
     * @return the message in GT
     * @throws InvalidCipherTextException if the decryption procedure is failure
     */
    Element decryption(PairingKeySerParameter publicKey, PairingKeySerParameter secretKey,
                       String[] ids, PairingCipherSerParameter ciphertext) throws InvalidCipherTextException;
}
