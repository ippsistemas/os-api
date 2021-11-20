package br.com.codiub.ordemservicos.model.repository.spec;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import br.com.codiub.ordemservicos.model.constant.TipoExecucoesEnum;
import br.com.codiub.ordemservicos.model.entity.Execucoes;
import br.com.codiub.ordemservicos.model.entity.Execucoes_;
import br.com.codiub.ordemservicos.model.filter.ExecucoesFilter;

public class ExecucoesSpec {

    private ExecucoesSpec() {}

    public static Specification<Execucoes> usingFilter(ExecucoesFilter filter, Pageable pageable) 
{
        return (root, query, builder) -> {
            List<Predicate> predicateList = new ArrayList<>();

            List<Order> orderList = QueryUtils.toOrders(pageable.getSort(), root, builder);
            query.orderBy(orderList);

            #LISTAR_ATRIBUTOS_SPEC_FILTER#

            /*
            if (filter.getId() != null) {
				predicateList.add(builder.equal(root.get
(Execucoes_.ID), filter.getId()));
			}

            if (filter.getNome() != null && !filter.getNome().isEmpty()) {
                predicateList.add(builder.like(builder.upper(root.get(Execucoes_.NOME)),
                    "%" + filter.getNome().toUpperCase() + "%"));
            }

            if (filter.getTipoPessoa() != null && !filter.getTipoPessoa().isEmpty()) {
                predicateList.add(builder.equal(
                    builder.upper(root.get(Execucoes_.TIPO_PESSOA)),
                    TipoPessoaEnum.valueOf(filter.getTipoPessoa().toUpperCase())));
            }
            */


            return builder.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
    }
   
}
