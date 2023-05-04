package br.edu.ifsp.pep.listener;

import br.edu.ifsp.pep.controller.PessoaController;
import br.edu.ifsp.pep.modelo.Pessoa;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import jakarta.inject.Inject;
import java.io.IOException;

public class Autorizacao implements PhaseListener {

    @Inject
    PessoaController pessoaController;
    
    @Override
    public void afterPhase(PhaseEvent event) {
        System.out.println("After: " + event.getPhaseId());
        
      //  HttpServletRequest request
        //        = (HttpServletRequest) event.getFacesContext()
        //                .getExternalContext()
        //                .getRequest();

        FacesContext ctx = event.getFacesContext();
        String pagina = ctx.getViewRoot().getViewId();
        System.out.println(pagina);

        Pessoa pessoaAutenticada = pessoaController.getPessoaAutenticada();
        if((pessoaAutenticada == null || !pessoaAutenticada.getTipo().equals("1")) && pagina.equals("/financeiro/folhapagamento.xhtml") ) {
            try{
                System.out.println("Redirecionamento....");
                ctx.getExternalContext()
                        .redirect("/phaselistener/index.xhtml");
            }catch(IOException ex)
            {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void beforePhase(PhaseEvent event) {
        System.out.println("Before: " + event.getPhaseId());
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

}
