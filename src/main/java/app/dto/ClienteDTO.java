package app.dto;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import app.model.Endereco;

public class ClienteDTO {		
				
		@NotBlank(message = "O Nome não pode ser vazio!")
		@Column(name = "NOME")
		@Size(max = 100, message = "O Nome deve conter no máximo 100 caracter!")
		private String nome;
		
		@NotBlank(message = "O Sobrenome não pode ser vazio!")
		@Column(name = "SOBRENOME")
		@Size(max = 100, message = "O Sobrenome deve conter no máximo 100 caracter!")
		private String sobrenome;
		
		@NotBlank(message = "A Senha não pode ser vazia!")
		@Column(name = "SENHA")
		@Size(min = 6, message = "A Senha deve conter no mínimo 6 caracter!")
		private String senha;
		
		@Column(name = "TELEFONE")
		private String telefone;
		
		@CPF(message = "CPF inválido!")
		@Column(name = "CPF", unique = true)
		@Size(min = 11, max = 11, message = "O CPF deve conter 11 digitos!")
		private String cpf;
		
		@NotBlank(message = "O E-mail não pode ser vazio!")
		@Column(name = "EMAIL")
		@Size(max = 100, message = "O E-mail deve conter no máximo 100 caracter!")
		private String email;
		
		@NotBlank(message = "O RG não pode ser vazio!")
		@Column(name = "RG", unique = true)
		private String rg;
		
		
		@OneToMany(mappedBy = "cliente", cascade = CascadeType.PERSIST)
		private List<Endereco> endereco;
	

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getSobrenome() {
			return sobrenome;
		}

		public void setSobrenome(String sobrenome) {
			this.sobrenome = sobrenome;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public String getTelefone() {
			return telefone;
		}

		public void setTelefone(String telefone) {
			this.telefone = telefone;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getRg() {
			return rg;
		}

		public void setRg(String rg) {
			this.rg = rg;
		}

		public List<Endereco> getEndereco() {
			return endereco;
		}

		public void setEndereco(List<Endereco> endereco) {
			this.endereco = endereco;
		}	
}
