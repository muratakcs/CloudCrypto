package cn.edu.buaa.crypto.algebra.genparams;

import cn.edu.buaa.crypto.algebra.serparams.PairingKeySerParameter;
import it.unisa.dia.gas.jpbc.Element;

/**
 * Created by Weiran Liu on 2016/11/20.
 *
 * Pairing encryption generation parameter.
 */
public abstract class PairingEncryptionGenerationParameter extends PairingEncapsulationGenerationParameter {
    private Element message;

    public PairingEncryptionGenerationParameter(PairingKeySerParameter publicKeyParameter, Element message) {
        super(publicKeyParameter);
        this.message = message.getImmutable();
    }

    public Element getMessage() { return this.message.duplicate(); }
}
