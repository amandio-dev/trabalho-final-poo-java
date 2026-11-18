public class Classes extends Personagem {

    public enum TipoClasse {
        MAGO,
        GUERREIRO,
        ARQUEIRO,
        ASSASSINO,
        PALADINO
    }

    private TipoClasse tipoClasse;

    public Classes(String nome, TipoClasse tipoClasse) {
        super(nome);

        this.tipoClasse = tipoClasse;

        configurarClasse();
    }

    // atribu valores inicias para cada classe
    private void configurarClasse() {

        switch (tipoClasse) {

            case MAGO:
                setVida(110);
                setForca(11);
                setInteligencia('D');
                setDefesa(6);
                setMana(80);
                setDestreza(8);
                break;

            case GUERREIRO:
                setVida(150);
                setForca(30);
                setInteligencia('D');
                setDefesa(25);
                setMana(31);
                setDestreza(8);
                break;

            case ARQUEIRO:
                setVida(110);
                setForca(15);
                setInteligencia('D');
                setDefesa(8);
                setMana(40);
                setDestreza(30);
                break;

            case ASSASSINO:
                setVida(105);
                setForca(25);
                setInteligencia('D');
                setDefesa(7);
                setMana(35);
                setDestreza(35);
                break;

            case PALADINO:
                setVida(160);
                setForca(25);
                setInteligencia('D');
                setDefesa(30);
                setMana(50);
                setDestreza(8);
                break;
        }
    }

    public TipoClasse getTipoClasse() {
        return tipoClasse;
    }

    // Executa os poderes da classe
    public boolean usarPoder(int poder, Personagem alvo) {

        if (poder < 1 || poder > 3) {
            return false;
        }

        switch (tipoClasse) {

            case MAGO:
                switch (poder) {
                    case 1:
                        return atacar(alvo, 35, 15);
                    case 2:
                        return aumentarDefesa(5, 12);
                    case 3:
                        recuperarMana(20);
                        return true;
                }
                break;

            case GUERREIRO:
                switch (poder) {
                    case 1:
                        return atacar(alvo, 30, 0);
                    case 2:
                        return aumentarDefesa(5, 0);
                    case 3:
                        return aumentarForca(5, 0);
                }
                break;

            case ARQUEIRO:
                switch (poder) {
                    case 1:
                        return atacar(alvo, 22, 5);
                    case 2:
                        return atacarDuasVezes(alvo, 12, 8);
                    case 3:
                        return atacar(alvo, 28, 10);
                }
                break;

            case ASSASSINO:
                switch (poder) {
                    case 1:
                        return atacar(alvo, 35, 8);
                    case 2:
                        return aumentarDestreza(5, 5);
                    case 3:
                        return atacarDuasVezes(alvo, 15, 8);
                }
                break;

            case PALADINO:
                switch (poder) {
                    case 1:
                        return atacar(alvo, 22, 10);
                    case 2:
                        return curarPersonagem(25, 10);
                    case 3:
                        return aumentarDefesa(7, 12);
                }
                break;
        }

        return false;
    }

    // Realiza um ataque(ataque basico)
    private boolean atacar(Personagem alvo, int dano, int custoMana) {

        if (alvo == null) {
            return false;
        }

        if (custoMana > 0 && !gastarMana(custoMana)) {
            return false;
        }

        return alvo.receberDano(dano);
    }

    // Realiza dois ataques(sequencia basica para todos)
    private boolean atacarDuasVezes(Personagem alvo, int dano, int custoMana) {

        if (alvo == null || !gastarMana(custoMana)) {
            return false;
        }

        boolean primeiro = alvo.receberDano(dano);
        boolean segundo = alvo.receberDano(dano);

        return primeiro || segundo;
    }

    // Aumenta a defesa
    private boolean aumentarDefesa(int quantidade, int custoMana) {

        if (custoMana > 0 && !gastarMana(custoMana)) {
            return false;
        }

        setDefesa(getDefesa() + quantidade);
        return true;
    }

    // Aumenta a força
    private boolean aumentarForca(int quantidade, int custoMana) {

        if (custoMana > 0 && !gastarMana(custoMana)) {
            return false;
        }

        setForca(getForca() + quantidade);
        return true;
    }

    // Aumenta a destreza(velocidade)
    private boolean aumentarDestreza(int quantidade, int custoMana) {

        if (custoMana > 0 && !gastarMana(custoMana)) {
            return false;
        }

        setDestreza(getDestreza() + quantidade);
        return true;
    }

    // Recupera vida do próprio personagem ou seja cura
    private boolean curarPersonagem(int quantidade, int custoMana) {

        if (!gastarMana(custoMana)) {
            return false;
        }

        curar(quantidade);
        return true;
    }
}
