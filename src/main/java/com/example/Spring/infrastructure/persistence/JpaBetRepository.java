package com.example.Spring.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Spring.domain.bet.Bet;
import com.example.Spring.domain.bet.Bet.BetId;
import com.example.Spring.domain.bet.ports.BetRepository;

@Repository
public class JpaBetRepository implements BetRepository {
    private final SpringJpaBetRepository springJpaRepository;

    public JpaBetRepository(SpringJpaBetRepository springJpaRepository) {
        this.springJpaRepository = springJpaRepository;
    }

    @Override
    public Bet save(Bet bet) {
        var entity = BetJpaEntity.fromDomain(bet);
        entity = springJpaRepository.save(entity);
        return entity.toDomain();
    }

    @Override
    public Optional<Bet> findById(BetId id) {
        return springJpaRepository.findById(id.value())
            .map(BetJpaEntity::toDomain);
    }

    @Override
    public List<Bet> findAll() {
        return springJpaRepository.findAll().stream()
            .map(BetJpaEntity::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(BetId id) {
        springJpaRepository.deleteById(id.value());
    }
}

@Repository
interface SpringJpaBetRepository extends JpaRepository<BetJpaEntity, Integer> {} 