package br.edu.ifsc.aluno.victor.controller.bloco;

import br.edu.ifsc.aluno.victor.Utils.BotaoUtils;
import br.edu.ifsc.aluno.victor.Utils.MensagensUtils;
import br.edu.ifsc.aluno.victor.Utils.WindowUtils;
import br.edu.ifsc.aluno.victor.controller.system.MenuController;
import br.edu.ifsc.aluno.victor.model.Bloco;
import br.edu.ifsc.aluno.victor.service.BlocoService;
import br.edu.ifsc.aluno.victor.view.bloco.FormularioBlocoView;
import br.edu.ifsc.aluno.victor.view.bloco.ListaBlocoView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

@Service
public class ListaBlocoController implements ActionListener {

    @Autowired
    private BlocoService blocoService;

    @Autowired
    private MenuController menuController;

    @Autowired
    private FormularioBlocoController formularioBlocoController;

    private ListaBlocoView listaBlocoView;

    public void init(ListaBlocoView listaBlocoView) {
        this.listaBlocoView = listaBlocoView;
        this.listaBlocoView.initButtons(this);
        this.definirTabela();
        this.carregaLista();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(listaBlocoView.getCadastrarBtn())) {
            this.abrirFormularioCadastro();
        } else  if (e.getSource().equals(listaBlocoView.getEditarBtn())) {
            this.clickEditar();
        } else  if (e.getSource().equals(listaBlocoView.getDeletarBtn())) {
            this.clickRemover();
        } else  if (e.getSource().equals(listaBlocoView.getFecharBtn())) {
            WindowUtils.Exit(listaBlocoView);
        } else  if (e.getSource().equals(listaBlocoView.getMenuBtn())) {
            BotaoUtils.VoltarMenu(listaBlocoView, menuController);
        }
    }

    public void abrir() {
        this.listaBlocoView.setVisible(true);
    }

    private void abrirFormularioCadastro() {
        FormularioBlocoView formularioBlocoView = new FormularioBlocoView(listaBlocoView);
        this.formularioBlocoController.init(formularioBlocoView);
        this.formularioBlocoController.abrir();
    }

    private void definirTabela() {
        String[] colunas = {"Id", "Descricao"};
        this.listaBlocoView.setColumnsNameListagem(colunas);
    }

    public void carregaLista() {
        DefaultTableModel tabela = (DefaultTableModel) this.listaBlocoView.getListagemTbt().getModel();
        for (int i = 0; i < tabela.getRowCount(); i++) {
            tabela.removeRow(i);
            this.listaBlocoView.getListagemTbt().repaint();
        }
        if (tabela.getRowCount() > 0) {
            tabela.removeRow(0);
        }
        List<Bloco> blocos = blocoService.consultar();
//        for (Object object : blocos) {
//            Bloco bloco = (Bloco) object;
//            tabela.addRow(new Object[]{bloco.getId(), bloco.getDescricao()});
//        }
        blocos.forEach(bloco -> tabela.addRow(new Object[]{bloco.getId(), bloco.getDescricao()}));
    }

    private void clickRemover() {
        JTable listagem = this.listaBlocoView.getListagemTbt();
        if(listagem.getSelectedRow() > -1){
            this.remover(listagem);
        }else{
            MensagensUtils.ErroRemover();
        }
    }

    private void remover(JTable tabela) {
        int codigoLinha = tabela.getSelectedRow();
        String descricao = tabela.getValueAt(codigoLinha, 1).toString();
        int isRemover = MensagensUtils.RemoverMensagem("Bloco", descricao);
        if (isRemover == 0) {
            Integer id = Integer.valueOf(tabela.getValueAt(codigoLinha, 0).toString());
            this.blocoService.deletar(id);
            DefaultTableModel dtm = (DefaultTableModel) tabela.getModel();
            dtm.removeRow(codigoLinha);
        }
    }

    private void clickEditar() {
        JTable listagem = this.listaBlocoView.getListagemTbt();
        if(listagem.getSelectedRow() > -1){
            int codigoLinha = listagem.getSelectedRow();
            Integer id = Integer.parseInt(listagem.getValueAt(codigoLinha, 0).toString());

            Bloco bloco = blocoService.consultar(id);
            FormularioBlocoView formularioBlocoView = new FormularioBlocoView(null);
            formularioBlocoController.init(formularioBlocoView, bloco);
            formularioBlocoController.abrir();
        }else{
            MensagensUtils.ErroEditar();
        }
    }
}
