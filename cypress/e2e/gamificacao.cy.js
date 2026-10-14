
// Testes automatizados da API Gamificação EAD.
// Valida a conclusão de cursos e as regras de liberação de recompensas.

describe('API Gamificação EAD', () => {

  // Testa a liberação de 3 cursos para média maior ou igual a 7.
  it('Deve liberar 3 cursos quando a média for maior ou igual a 7', () => {

    cy.request({
      method: 'POST',
      url: 'http://localhost:8080/api/alunos/conclusao',
      body: {
        nomeAluno: 'Aluno Teste Cypress',
        nomeCurso: 'DevOps e QA',
        mediaFinal: 8.5
      }
    }).then((response) => {

      expect(response.status).to.eq(201);
      expect(response.body.nomeAluno).to.eq('Aluno Teste Cypress');
      expect(response.body.mediaFinal).to.eq(8.5);
      expect(response.body.cursosLiberados).to.eq(3);

    });

  });

  // Testa a regra de negócio para alunos com média inferior a 7.
  it('Nao deve liberar cursos quando a media for inferior a 7', () => {

    cy.request({
      method: 'POST',
      url: 'http://localhost:8080/api/alunos/conclusao',
      body: {
        nomeAluno: 'Aluno Teste Reprovado',
        nomeCurso: 'DevOps e QA',
        mediaFinal: 6.5
      }
    }).then((response) => {

      expect(response.status).to.eq(201);
      expect(response.body.mediaFinal).to.eq(6.5);
      expect(response.body.cursosLiberados).to.eq(0);

    });

  });

});

